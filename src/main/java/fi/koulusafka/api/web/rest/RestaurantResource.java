package fi.koulusafka.api.web.rest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.koulusafka.api.constants.ApiConstants;
import fi.koulusafka.api.domain.Restaurant;
import fi.koulusafka.api.domain.enumeration.Brand;
import fi.koulusafka.api.service.RestaurantService;
import fi.koulusafka.api.service.dto.RestaurantDTO;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Restaurant.
 */
@RestController
@RequestMapping("/api")
public class RestaurantResource {

	private final Logger log = LoggerFactory.getLogger(RestaurantResource.class);

	private static final String ENTITY_NAME = "restaurant";

	private final RestaurantService restaurantService;

	public RestaurantResource(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	

	@GetMapping(ApiConstants.VERSION1 + "/restaurant")
	public ResponseEntity<Restaurant> getRestaurant(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", required = false) Long id) {

		if (id != null) {
			Restaurant restaurant = restaurantService.findOneById(id);
			return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurant));
		}

		if (!StringUtils.isBlank(name)) {
			Restaurant restaurant = restaurantService.findOneByWebsiteUrl(name);
			return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurant));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@GetMapping(ApiConstants.VERSION1 + "/restaurants/all")
	public List<RestaurantDTO> getAllRestaurants() {
		log.debug("REST request to get all Restaurants");
		return restaurantService.findAll();
	}
	
	@GetMapping(ApiConstants.VERSION1 + "/restaurants")
	public ResponseEntity<List<RestaurantDTO>> getRestaurants(
			@RequestParam(value = "brand", required = false) String brandName,
			@RequestParam(value = "post_office", required = false) String postOffice,
			@RequestParam(value = "postal_code", required = false) String postalCode) {


		if (!StringUtils.isBlank(brandName) && !StringUtils.isBlank(postOffice)) {
			return ResponseUtil.wrapOrNotFound(
					Optional.ofNullable(restaurantService.findAllByBrandAndPostOffice(brandName, postOffice)));
		}
			else if (!StringUtils.isBlank(brandName)) {
				return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantService.findAllByBrand(brandName)));
		}
			else if (!StringUtils.isBlank(postOffice)) {
				return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantService.findAllByPostOffice(postOffice)));
		}
		
		if (!StringUtils.isBlank(postalCode)) {
			return ResponseUtil.wrapOrNotFound(Optional.ofNullable(restaurantService.findAllByPostalCode(postalCode)));
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping(ApiConstants.VERSION1 + "/restaurants/location")
	public List<RestaurantDTO> findNearestRestaurants(
			@RequestParam(value="latitude", required = true) Double latitude,
			@RequestParam(value="longitude", required = true) Double lontitude) {
		return restaurantService.findAllNearestRestaurants(latitude, lontitude);
	}



	@GetMapping(ApiConstants.VERSION1 + "/restaurants/brands")
	public ArrayList<Brand> getBrands() {
		return new ArrayList<Brand>(EnumSet.allOf(Brand.class));
	}


}
