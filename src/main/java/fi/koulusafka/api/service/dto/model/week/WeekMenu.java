package fi.koulusafka.api.service.dto.model.week;

import java.util.List;

public class WeekMenu {
	
	private String restaurantName;
	private String brand;
	private String errorText;
	private List<WeekMenuDay> weekDays;
	
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public WeekMenu setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
		return this;
	}
	public String getErrorText() {
		return errorText;
	}
	public WeekMenu setErrorText(String errorText) {
		this.errorText = errorText;
		return this;
	}
	public String getBrand() {
		return brand;
	}
	public WeekMenu setBrand(String brand) {
		this.brand = brand;
		return this;
	}
	public List<WeekMenuDay> getWeekDays() {
		return weekDays;
	}
	public WeekMenu setWeekDays(List<WeekMenuDay> weekDays) {
		this.weekDays = weekDays;
		return this;
	}
	@Override
	public String toString() {
		return "WeekMenu [restaurantName=" + restaurantName + ", brand=" + brand + ", errorText=" + errorText
				+ ", weekDays=" + weekDays + "]";
	}
	
	
	

}
