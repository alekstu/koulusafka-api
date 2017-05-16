package fi.koulusafka.api.service.restaurant;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.koulusafka.api.constants.RestaurantConstants;
import fi.koulusafka.api.service.connection.ConnectionUtil;
import fi.koulusafka.api.service.dto.json.unicafe.UniCafeWeek;
import fi.koulusafka.api.service.dto.json.unicafe.UniCafeWeekDay;
import fi.koulusafka.api.service.dto.json.unicafe.UniCafeWeekDayFood;
import fi.koulusafka.api.service.dto.model.food.Food;
import fi.koulusafka.api.service.dto.model.week.WeekMenu;
import fi.koulusafka.api.service.dto.model.week.WeekMenuDay;
import fi.koulusafka.api.service.util.UrlUtil;



@Service
public class UniCafeService {

	private static final Logger log = LoggerFactory.getLogger(UniCafeService.class);

	
	public UniCafeWeek getUniCafeJson(String restaurantId, String languageKey) {

			try {

				String url = UrlUtil.generateUniCafeWeekUrl(restaurantId, languageKey);
				String json = ConnectionUtil.getResponseBody(url);

				ObjectMapper mapper = new ObjectMapper();
				
				if (json.length() != 0) {
					UniCafeWeek uniCafeWeek = mapper.readValue(json, UniCafeWeek.class);
					return uniCafeWeek;
				}
			

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;

	}

	public WeekMenu getUniCafeWeekMenu(UniCafeWeek json, String languageKey) {

		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		WeekMenu weekMenu = new WeekMenu();
		weekMenu.setRestaurantName(json.getRestaurantName());

		List<WeekMenuDay> weekDays = new ArrayList<WeekMenuDay>();
		for (UniCafeWeekDay day : json.getWeekDays()) {
			WeekMenuDay weekDay = new WeekMenuDay();

			weekDay.setWeekDay(day.getWeekDay());

			if (day.getWeekDay().contains(f.format(new Date()))) {
				weekDay.setToday(true);
			}

			List<Food> foods = new ArrayList<>();

			for (UniCafeWeekDayFood jsonFood : day.getFoods()) {
				Food food = new Food();

				if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_EN) && jsonFood.getFoodNameEn() != null) {

					food.setName(jsonFood.getFoodNameEn());

					StringBuilder properties = new StringBuilder();
					for (String s : jsonFood.getProperties()) {
						properties.append(s);
						properties.append(", ");
					}
					food.setProperties(properties.toString());
					food.setPrice(jsonFood.getPrice());

				} else if (languageKey.equals(RestaurantConstants.LANGUAGE_KEY_SV)
						&& jsonFood.getFoodNameSv() != null) {

					food.setName(jsonFood.getFoodNameEn());

					StringBuilder properties = new StringBuilder();
					for (String s : jsonFood.getProperties()) {

						if (properties.length() != 0) {
							properties.append(", ");
						}
						properties.append(s);

					}
					food.setProperties(properties.toString());
					food.setPrice(jsonFood.getPrice());
					food.setExtra(jsonFood.getNutritional_info());

				} else {

					food.setName(jsonFood.getFoodNameFi());

					StringBuilder properties = new StringBuilder();
					for (String s : jsonFood.getProperties()) {
						properties.append(s);
						properties.append(", ");
					}
					food.setProperties(properties.toString());
					food.setDescription(jsonFood.getDescription());
					food.setPrice(jsonFood.getPrice());
					food.setExtra(jsonFood.getNutritional_info());

				}

				foods.add(food);

			}

			weekDay.setFoods(foods);
			weekDays.add(weekDay);
		}
		weekMenu.setWeekDays(weekDays);

		return weekMenu;

	}

}
