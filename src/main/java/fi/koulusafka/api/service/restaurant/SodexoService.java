package fi.koulusafka.api.service.restaurant;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.koulusafka.api.constants.RestaurantConstants;
import fi.koulusafka.api.service.connection.ConnectionUtil;
import fi.koulusafka.api.service.dto.json.sodexo.SodexoWeek;
import fi.koulusafka.api.service.dto.json.sodexo.SodexoWeekDay;
import fi.koulusafka.api.service.dto.model.food.Food;
import fi.koulusafka.api.service.dto.model.week.WeekMenu;
import fi.koulusafka.api.service.dto.model.week.WeekMenuDay;
import fi.koulusafka.api.service.util.UrlUtil;

@Service
public class SodexoService {

	private static final Logger log = LoggerFactory.getLogger(SodexoService.class);


	@Transactional(readOnly = true)
	public SodexoWeek getSodexoJson(String restaurantId) {

		String url = UrlUtil.generateSodexoWeekUrl(restaurantId);

		try {
			SodexoWeek sodexoMenu = null;

			ObjectMapper mapper = new ObjectMapper();
			String json = ConnectionUtil.getResponseBody(url);
			try {
				sodexoMenu = mapper.readValue(json, SodexoWeek.class);
			} catch (JsonMappingException jsonMappingException) {
				jsonMappingException.printStackTrace();

			}

			return sodexoMenu;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return new SodexoWeek();
	}

	public WeekMenu getSodexoWeekMenu(SodexoWeek week, String languageKey) {
		
		if (week != null) {

			WeekMenu weekMenu = new WeekMenu().setBrand(RestaurantConstants.RESTAURANT_BRAND_SODEXO).setRestaurantName(week.getMeta().get("ref_title"))
					.setWeekDays(new ArrayList<WeekMenuDay>());
			String timeStamp = week.getMeta().get("generated_timestamp");

			week.getWeekDay().entrySet().stream().forEach(day -> {
				weekMenu.getWeekDays()
						.add(new WeekMenuDay().setWeekDay("").setFoods(getSodexoWeekFood(day.getValue(), languageKey)));
			});
			Long timeInLong = Long.parseLong(timeStamp);
			
			LocalDate date =  Instant.ofEpochSecond(timeInLong).atZone(ZoneId.systemDefault()).toLocalDate();
			log.debug("date = " + timeInLong);
			LocalDate day = date.with(DayOfWeek.MONDAY);
			
			for (WeekMenuDay weekMenuDay : weekMenu.getWeekDays()) {
				weekMenuDay.setWeekDay(day.toString());
				if (date.isEqual(day)) {
					weekMenuDay.setToday(true);
				}
				
				day = day.plusDays(1);
			}
			return weekMenu;
		}

		return new WeekMenu();

	}

	private List<Food> getSodexoWeekFood(List<SodexoWeekDay> weekFoodList, String languageKey) {
		return weekFoodList
				.stream()
				.map(day -> getSodexoFoodFromDay(day, languageKey))
				.collect(Collectors.toCollection(ArrayList::new));

	}

	private Food getSodexoFoodFromDay(SodexoWeekDay jsonDay, String languageKey) {
		
		Food food = new Food();

		if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_EN)|| languageKey.equals(RestaurantConstants.LANGUAGE_KEY_SV)) {

			if (jsonDay.getFoodNameEn() == null) {
				if (jsonDay.getFoodNameFi().contains("/")) {
					food.setName(getFoodName(jsonDay.getFoodNameFi(), RestaurantConstants.LANGUAGE_KEY_EN));
				} else {
					food.setName(jsonDay.getFoodNameFi());
				}
			} else {
				food.setName(jsonDay.getFoodNameEn());
				 if (jsonDay.getDescriptionEn() != null) {
					 food.setDescription(jsonDay.getDescriptionEn());
				 }
			}

		} else {
			food.setName(getFoodName(jsonDay.getFoodNameFi(), RestaurantConstants.LANGUAGE_KEY_FI));
			food.setDescription(jsonDay.getDescriptionFin());

		}
		food.setProperties(jsonDay.getProperties());
		food.setPrice(jsonDay.getPrice());

		return food;
		
	}

	/*
	 * 
	
	public WeekMenu getSodexoWeekMenu(SodexoWeek sodexo, String languageKey) {

		if (sodexo != null) {

			Locale locale = Locale.forLanguageTag(languageKey);

			WeekMenu weekMenu = new WeekMenu();
			List<WeekMenuDay> weekDays = new ArrayList<WeekMenuDay>();

			weekMenu.setRestaurantName(sodexo.getMeta().get("ref_title"));
			int today = getDayNumber(sodexo.getMeta().get("generated_timestamp"));
			int weekDayCount = 0;

			for (List<SodexoWeekDay> days : sodexo.getWeekDay().values()) {
				weekDayCount += 1;
				log.debug("DAY >>> " + days.toString());
				// WEEDAY = COUNT GET MESSAGE
				WeekMenuDay weekMenuDay = new WeekMenuDay();
				weekMenuDay.setWeekDay("");

				// +1 cause Calendar date starts at sundayy
				if (weekDayCount + 1 == today) {
					weekMenuDay.setToday(true);
				}

				List<Food> foods = new ArrayList<>();

				for (SodexoWeekDay jsonDay : days) {

					Food food = new Food();

					if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_EN)
							|| languageKey.equals(RestaurantConstants.LANGUAGE_KEY_SV)) {

						if (jsonDay.getFoodNameEn() == null) {
							if (jsonDay.getFoodNameFi().contains("/")) {
								food.setName(getFoodName(jsonDay.getFoodNameFi(), RestaurantConstants.LANGUAGE_KEY_EN));
							} else {
								food.setName(jsonDay.getFoodNameFi());
								weekMenu.setErrorText("json.not.found.retry");
							}
						} else {
							food.setName(jsonDay.getFoodNameEn());

						}

						if (jsonDay.getFoodNameEn() != null) {
							food.setDescription(jsonDay.getDescriptionEn());

						} else {
							food.setDescription("No description");
						}

						if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_SV)) {
							weekMenu.setErrorText("json.not.found.retry");
						}

					} else {
						food.setName(getFoodName(jsonDay.getFoodNameFi(), RestaurantConstants.LANGUAGE_KEY_FI));
						food.setDescription(jsonDay.getDescriptionFin());

					}
					food.setProperties(jsonDay.getProperties());
					food.setPrice(jsonDay.getPrice());
					foods.add(food);

				}
				weekMenuDay.setFoods(foods);

				weekDays.add(weekMenuDay);
			}
			weekMenu.setWeekDays(weekDays);
			return weekMenu;

		} else {
			return new WeekMenu().setErrorText("no.menu.error").setWeekDays(new ArrayList<>());
		}

	}
 */
	private String getFoodName(String foodName, String languageKey) {
		if (!foodName.contains("/")) {
			return foodName;
		} else {
			String[] foodNames = foodName.split("/");
			if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_EN)) {
				return foodNames[1].trim();
			}

			if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_FI)) {
				return foodNames[0].trim();
			}

		}
		return foodName;
	}

	private int getDayNumber(String timeStamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(timeStamp) * 1000);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
