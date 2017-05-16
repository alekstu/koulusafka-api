package fi.koulusafka.api.service.restaurant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.koulusafka.api.constants.RestaurantConstants;
import fi.koulusafka.api.service.connection.ConnectionUtil;
import fi.koulusafka.api.service.dto.json.amica.AmicaWeek;
import fi.koulusafka.api.service.dto.json.amica.AmicaWeekDay;
import fi.koulusafka.api.service.dto.json.amica.AmicaWeekDayFood;
import fi.koulusafka.api.service.dto.model.food.Food;
import fi.koulusafka.api.service.dto.model.week.WeekMenu;
import fi.koulusafka.api.service.dto.model.week.WeekMenuDay;
import fi.koulusafka.api.service.util.UrlUtil;


@Service
public class AmicaService {

	private final static Logger log = LoggerFactory.getLogger(AmicaService.class);
	

  


	private String url = "";
	private ObjectMapper mapper = null;

	public AmicaWeek getAmicaJson(String restaurantId, String languageKey) {
	
		try {

			boolean retrieved = false;
	
			url = UrlUtil.generateAmicaUrl(restaurantId, languageKey);
			String json = ConnectionUtil.getResponseBody(url);
		
			// if json was not found in language, try english
			if (json.contains(RestaurantConstants.ERROR_RESTAURANT_NOT_FOUND)) {
				json = ConnectionUtil
						.getResponseBody(UrlUtil.generateAmicaUrl(restaurantId, RestaurantConstants.LANGUAGE_KEY_EN));
				retrieved = true;
			}

			mapper = new ObjectMapper();
			AmicaWeek menu = mapper.readValue(json, AmicaWeek.class);

			if (retrieved == true) {
				menu.setErrorText("error.not.found");
			}

			return menu;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public WeekMenu getAmicaWeekMenu(AmicaWeek menu, String languageKey) {

		if (menu != null) {
		
			WeekMenu weekMenu = new WeekMenu()
									.setBrand(RestaurantConstants.RESTAURANT_BRAND_AMICA)
									.setRestaurantName(menu.getRestaurantName())
									.setErrorText(menu.getErrorText());

			List<WeekMenuDay> weekDays = new ArrayList<WeekMenuDay>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			for (AmicaWeekDay f : menu.getMenusForDays()) {


				WeekMenuDay day = new WeekMenuDay();
				day.setWeekDay(formatter.format(f.getDate()));

				if (formatter.format(f.getDate()).equals(formatter.format(new Date()))) {
					day.setToday(true);
				}

				List<Food> foods = new ArrayList<Food>();
				for (AmicaWeekDayFood foodLine : f.getSetMenus()) {

					Food food = new Food();

					if (foodLine.getComponents().size() == 1) {
						food.setName(foodLine.getComponents().get(0).substring(0,
								foodLine.getComponents().get(0).indexOf("(") - 1));

						StringBuilder properties = new StringBuilder();
						properties.append("(");

						Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(foodLine.getComponents().get(0));
						while (m.find()) {
							properties.append(m.group(1));
						}
						properties.append(")");
						food.setProperties(properties.toString());
						food.setExtra(foodLine.getName());

					} else {

						for (String s : foodLine.getComponents()) {
							if (food.getName() == null) {
								food.setName(s.substring(0, s.indexOf("(") - 1) + " + ");

							} else {
								food.setName(food.getName() + s.substring(0, s.indexOf("(") - 1));
							}
							StringBuilder properties = new StringBuilder();
							properties.append("(");

							Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(foodLine.getComponents().get(0));
							while (m.find()) {
								properties.append(m.group(1));
							}

							properties.append(")");
							if (food.getProperties() == null) {
								food.setProperties(properties.toString());
							}

						}
						food.setExtra(foodLine.getName());
					}

					foods.add(food);
				}

				day.setFoods(foods);
				weekDays.add(day);

	
			}

			weekMenu.setWeekDays(weekDays);

			return weekMenu;
			
		}
	return new WeekMenu();
	}

}
