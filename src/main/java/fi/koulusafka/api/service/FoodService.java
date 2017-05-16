package fi.koulusafka.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.koulusafka.api.constants.RestaurantConstants;
import fi.koulusafka.api.domain.Restaurant;
import fi.koulusafka.api.domain.enumeration.Brand;
import fi.koulusafka.api.service.dto.RestaurantDTO;
import fi.koulusafka.api.service.dto.json.amica.AmicaWeek;
import fi.koulusafka.api.service.dto.json.sodexo.SodexoWeek;
import fi.koulusafka.api.service.dto.json.unicafe.UniCafeWeek;
import fi.koulusafka.api.service.dto.model.week.WeekMenu;
import fi.koulusafka.api.service.restaurant.AmicaService;
import fi.koulusafka.api.service.restaurant.SodexoService;
import fi.koulusafka.api.service.restaurant.UniCafeService;
import io.github.jhipster.web.util.ResponseUtil;

@Service
public class FoodService {

	private final static Logger log = LoggerFactory.getLogger(FoodService.class);

	private final AmicaService amicaService;

	private final SodexoService sodexoService;

	private final UniCafeService uniCafeService;

	private final RestaurantService restaurantService;

	public FoodService(AmicaService amicaService, SodexoService sodexoService, UniCafeService uniCafeService,
			RestaurantService restaurantService) {
		this.amicaService = amicaService;
		this.sodexoService = sodexoService;
		this.uniCafeService = uniCafeService;
		this.restaurantService = restaurantService;

	}
	
	@Transactional(readOnly = true)
	public WeekMenu getWeekMenu(Long id, String languageKey) {

		Restaurant restaurant = restaurantService.findOneById(id);

		languageKey = getLanguageKey(languageKey);

		if (restaurant.getBrand() != null) {

			increaseHits(restaurant);

			Brand brand = restaurant.getBrand();

			switch (brand.toString()) {

			case RestaurantConstants.RESTAURANT_BRAND_AMICA:
				return amicaService.getAmicaWeekMenu(
						amicaService.getAmicaJson(restaurant.getRestaurantId(), languageKey), languageKey);

			case RestaurantConstants.RESTAURANT_BRAND_SODEXO:
				return sodexoService.getSodexoWeekMenu(sodexoService.getSodexoJson(restaurant.getRestaurantId()),
						languageKey);

			case RestaurantConstants.RESTAURANT_BRAND_UNICAFE:
				return uniCafeService.getUniCafeWeekMenu(
						uniCafeService.getUniCafeJson(restaurant.getRestaurantId(), languageKey), languageKey);

			default:
				return new WeekMenu().setRestaurantName(restaurant.getName()).setErrorText("no.menu.error")
						.setWeekDays(new ArrayList<>());

			}

		}

		return new WeekMenu().setRestaurantName(restaurant.getName()).setErrorText("no.menu.error")
				.setWeekDays(new ArrayList<>());

	}

	@Transactional(readOnly = true)
	public ResponseEntity<WeekMenu> getMenu(Long id, String name, String type, String lang) {

		if (id != null) {

			if (StringUtils.isBlank(type) || type.equals("week")) {
				return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getWeekMenu(id, lang)));
			}

			if (type.equals("today")) {
				return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getTodayMenu(id, lang)));
			}

		}

		if (!StringUtils.isBlank(name)) {
			if (StringUtils.isBlank(type) || type.equals("week")) {
				return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getWeekMenuByWebsiteUrl(name, lang)));
			}

			if (type.equals("today")) {
				return ResponseUtil
						.wrapOrNotFound(Optional.ofNullable(getTodayMenu(getRestaurantIdFromWebsiteUrl(name), lang)));
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Transactional(readOnly = true)
	public ResponseEntity<List<WeekMenu>> getMultipleMenus(List<Long> restaurants, String type, String lang) {

		if (StringUtils.isBlank(type) || type.equals("today")) {
			return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getTodayMenus(restaurants, lang)));
		} else {
			return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getWeekMenus(restaurants, lang)));
		}
		
	
	}


	public Long getRestaurantIdFromWebsiteUrl(String websiteUrl) {
		return restaurantService.findOneByWebsiteUrl(websiteUrl).getId();
	}

	@Transactional(readOnly = true)
	public WeekMenu getWeekMenuByWebsiteUrl(String websiteUrl, String languageKey) {
		return getWeekMenu(restaurantService.findOneByWebsiteUrl(websiteUrl).getId(), languageKey);
	}

	@Transactional(readOnly = true)
	public List<WeekMenu> getWeekMenus(List<Long> restaurants, String language) {
		return restaurants.stream().map(id -> getWeekMenu(id, language))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Transactional(readOnly = true)
	public List<WeekMenu> getTodayMenus(List<Long> restaurants, String languageKey) {
		return restaurants.stream().map(id -> getTodayMenu(id, languageKey))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Transactional(readOnly = true)
	public WeekMenu getTodayMenu(Long id, String languageKey) {

		WeekMenu weekMenu = getWeekMenu(id, languageKey);

		if (weekMenu.getWeekDays().size() != 0) {
			weekMenu.getWeekDays().removeIf(day -> !day.isToday());

			return weekMenu;
		} else {
			return weekMenu;
		}

	}

	@Transactional(readOnly = true)
	public SodexoWeek getSodexoJson(String restaurantId) throws IOException {
		return sodexoService.getSodexoJson(restaurantId);
	}

	@Transactional(readOnly = true)
	public WeekMenu getSodexoWeekListSimple(SodexoWeek json, String languageKey) {
		return sodexoService.getSodexoWeekMenu(json, languageKey);
	}

	@Transactional(readOnly = true)
	public AmicaWeek getAmicaJson(String restaurantId) throws IOException {
		return amicaService.getAmicaJson(restaurantId, null);
	}

	@Transactional(readOnly = true)
	public WeekMenu getAmicaWeekListSimple(AmicaWeek json, String languageKey) {
		return amicaService.getAmicaWeekMenu(json, languageKey);
	}

	@Transactional(readOnly = true)
	public UniCafeWeek getUniCafeJson(String restaurantId, String languageKey) {
		return uniCafeService.getUniCafeJson(restaurantId, languageKey);
	}

	@Transactional(readOnly = true)
	public WeekMenu getUniCafeWeekMenuSimple(UniCafeWeek json, String languageKey) {
		return uniCafeService.getUniCafeWeekMenu(json, languageKey);
	}

	@Transactional(readOnly = false)
	private void increaseHits(Restaurant restaurant) {
		restaurant.setHits(restaurant.getHits() + 1);
		restaurantService.save(restaurant);
	}
	
	@Transactional(readOnly = true)
	private String getLanguageKey(String languageKey) {
		if (languageKey == null || !Arrays.asList(RestaurantConstants.LANGUAGE_KEY_FI,
				RestaurantConstants.LANGUAGE_KEY_EN, RestaurantConstants.LANGUAGE_KEY_SV).contains(languageKey)) {
			return RestaurantConstants.LANGUAGE_KEY_FI;
		} else {
			return languageKey;
		}
	}
	

	public void checkRestaurants() throws IOException {
		List<WeekMenu> weekMenus = new ArrayList<>();

		List<RestaurantDTO> restaurants = restaurantService.findAllByBrand("amica");

		for (RestaurantDTO restaurant : restaurants) {
			WeekMenu menu = getWeekMenu(restaurant.getId(), "fi");

			if (menu.getWeekDays() == null || menu.getWeekDays().size() == 0) {
				weekMenus.add(menu);
			}
		}

		weekMenus.stream().forEach(m -> log.debug(m.getRestaurantName() + " -no menu"));

	}


}
