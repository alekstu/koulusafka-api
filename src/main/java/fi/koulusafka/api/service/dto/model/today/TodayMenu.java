package fi.koulusafka.api.service.dto.model.today;

import java.util.List;

import fi.koulusafka.api.service.dto.model.food.Food;



public class TodayMenu {

	private String restaurantName;
	private Long restaurantId;
	private String websiteUrl;
	private String weekDay;
	private List <Food> foods;
	
	public String getRestaurantName() {
		return restaurantName;
	}
	
	public Long getRestaurantId() {
		return restaurantId;
	}
	
	public TodayMenu setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
		return this;
	}
	
	public TodayMenu setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
		return this;
	}
	
	public String getWeekDay() {
		return weekDay;
	}
	
	public TodayMenu setWeekDay(String weekDay) {
		this.weekDay = weekDay;
		return this;
	}
	
	public List<Food> getFoods() {
		return foods;
	}
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	
	public TodayMenu setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
		return this;
	}
	
	public TodayMenu setFoods(List<Food> foods) {
		this.foods = foods;
		return this;
	}
	
	@Override
	public String toString() {
		return "TodayMenu [restaurantName=" + restaurantName + ", restaurantId=" + restaurantId + ", websiteUrl="
				+ websiteUrl + ", weekDay=" + weekDay + ", foods=" + foods + "]";
	}
	
}
