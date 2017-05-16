package fi.koulusafka.api.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import fi.koulusafka.api.domain.Restaurant;
import fi.koulusafka.api.domain.enumeration.Brand;
import fi.koulusafka.api.repository.RestaurantRepository;
import fi.koulusafka.api.service.dto.RestaurantDTO;
import fi.koulusafka.api.service.util.LocationUtil;



/**
 * Service Implementation for managing Restaurant.
 */
@Service
@Transactional
public class RestaurantService {

    private final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll()
        		.stream()
        		.map(restaurant -> restaurantToRestaurantDTO(restaurant))
        		.collect(Collectors.toCollection(ArrayList::new));      
    }
    
    public List<Restaurant> findAllFull() {
    	return restaurantRepository.findAll();
    }
   
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAllByBrand(String brandName) {
    	Brand brand = getValidBrand(brandName);
		return restaurantRepository.findByBrand(brand)
				.map(restaurants -> restaurantsToRestaurantDTOs(restaurants))
				.orElse(Collections.emptyList());
    }
    
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAllByPostOffice(String postOffice) {
    	return restaurantRepository.findByPostOfficeIgnoreCase(postOffice)
    			.map(restaurants -> restaurantsToRestaurantDTOs(restaurants))
    			.orElse(Collections.emptyList());
    			
	}
	
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAllByPostalCode(String postalCode) {
     	return restaurantRepository.findByPostalCode(postalCode)
    			.map(restaurants -> restaurantsToRestaurantDTOs(restaurants))
    			.orElse(Collections.emptyList());
	}

    
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAllByBrandAndPostOffice(String brandName, String postOffice) {
    	Brand brand = getValidBrand(brandName);
    	return restaurantRepository.findByBrandAndPostOfficeIgnoreCase(brand, postOffice)
    			.map(restaurants -> restaurantsToRestaurantDTOs(restaurants))
    			.orElse(Collections.emptyList());
    			
	}
    
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findAllNearestRestaurants(Double latitude, Double longitude) {
    	List<Restaurant> restaurants = 	
    			LocationUtil.getNearestRestaurants(restaurantRepository.findAll(), latitude, longitude);
    	return restaurantsToRestaurantDTOs(restaurants);
    }
					
        		
    
    @Transactional(readOnly = false)
    public void disableRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findOne(id);
		if (restaurant.isEnabled() == true) {
			restaurant.setEnabled(false);
			restaurant.setDisabledDate(ZonedDateTime.now());
			restaurantRepository.save(restaurant);
		}
	
		
	}

    public Restaurant save(Restaurant restaurant) {
        log.debug("Request to save Restaurant : {}", restaurant);
        Restaurant result = restaurantRepository.save(restaurant);
        return result;
    }
 
    @Transactional(readOnly = true)
    public Restaurant findOneById(Long id) {
        log.debug("Request to get Restaurant : {}", id);
        Restaurant restaurant = restaurantRepository.findOne(id);
        return restaurant;
    }
    
    @Transactional(readOnly = true) 
    public Restaurant findOneByWebsiteUrl(String websiteUrl) {
        return restaurantRepository.findByWebsiteUrlIgnoreCase(websiteUrl);

    }


    public void delete(Long id) {
        log.debug("Request to delete Restaurant : {}", id);
        restaurantRepository.delete(id);
    }
    
    private Brand getValidBrand(String brandName) {
    	if (brandName != null && !brandName.isEmpty()) {
    		
    		brandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1);
    		
    		if (EnumUtils.isValidEnum(Brand.class, brandName)) {
        		return Brand.valueOf(brandName);
        	} else {
        		return null;
        	}
    	}
    	return null;
    	
    }

	private List<RestaurantDTO> restaurantsToRestaurantDTOs(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> restaurantToRestaurantDTO(restaurant))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	private RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant) {
		return new RestaurantDTO()
				.setId(restaurant.getId())
				.setBrand(restaurant.getBrand())
				.setName(restaurant.getWebsiteUrl())
				.setFullName(restaurant.getName());
		
	}

	



	
}
