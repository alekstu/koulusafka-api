package fi.koulusafka.api.service.dto.model.week;

import java.util.List;

import fi.koulusafka.api.service.dto.model.food.Food;

public class WeekMenuDay {

	
	private String weekDay;
	private boolean today;
	private List<Food> foods;
	
	
	public String getWeekDay() {
		return weekDay;
	}
	public WeekMenuDay setWeekDay(String weekDay) {
		this.weekDay = weekDay;
		return this;
	}
	public boolean isToday() {
		return today;
	}
	public WeekMenuDay setToday(boolean today) {
		this.today = today;
		return this;
	}
	public List<Food> getFoods() {
		return foods;
	}
	public WeekMenuDay setFoods(List<Food> foods) {
		this.foods = foods;
		return this;
	}
	@Override
	public String toString() {
		return "WeekMenuDay [weekDay=" + weekDay + ", today=" + today + ", foods=" + foods + "]";
	}
	
	
}
