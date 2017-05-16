package fi.koulusafka.api.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
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
import fi.koulusafka.api.service.FoodService;
import fi.koulusafka.api.service.dto.model.week.WeekMenu;
import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class FoodResource {
	
	private final Logger log = LoggerFactory.getLogger(FoodResource.class);
	
	private final FoodService foodService;
	
	
	 public FoodResource(FoodService foodService) {
	        this.foodService = foodService;
	    }
	
/* 
	 @GetMapping(ApiConstants.VERSION1 + "/menu/test")
	public void eatShit() throws IOException {
		 foodService.checkRestaurants();
	 }
	*/
	 
	 
	 @GetMapping(ApiConstants.VERSION1 + "menu/restaurants")
	 public ResponseEntity<List<WeekMenu>> getWeekMenus(
			 	@RequestParam(value="id", required = true) List<Long> restaurants,
			 	@RequestParam(value="type", required = false) String type,
			 	@RequestParam(value="lang", required = false) String lang) throws URISyntaxException, IOException {
		 return foodService.getMultipleMenus(restaurants, type, lang);
	 }
	 
	 @GetMapping(ApiConstants.VERSION1 + "/menu/restaurant")
	    public ResponseEntity<WeekMenu> getMenu(
	    		@RequestParam(value = "id", required = false) Long id,
	    		@RequestParam(value="name", required = false) String name,
	    		@RequestParam(value="type", required = false) String type,
	    		@RequestParam(value="lang", required = false) String lang) throws URISyntaxException, IOException {
		 
		 return foodService.getMenu(id, name, type, lang);
	 }
	    
	    
}
