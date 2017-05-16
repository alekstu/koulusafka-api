package fi.koulusafka.api.service.dto.json.unicafe;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UniCafeWeekDay {
	
	
	@JsonProperty("title")
	private String weekDay;
	
	@JsonProperty("foods")
	private List<UniCafeWeekDayFood> foods;
	
	public String getWeekDay() {
		return weekDay;
	}
	
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
	public List<UniCafeWeekDayFood> getFoods() {
		return foods;
	}
	
	public void setFoods(List<UniCafeWeekDayFood> foods) {
		this.foods = foods;
	}
	
	@Override
	public String toString() {
		return "UniCafeWeekDay [weekDay=" + weekDay + ", foods=" + foods + "]";
	}
	


}
