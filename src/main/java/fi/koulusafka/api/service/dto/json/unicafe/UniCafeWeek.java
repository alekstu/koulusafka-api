package fi.koulusafka.api.service.dto.json.unicafe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UniCafeWeek {
	
	
	@JsonProperty("week")
	private List<UniCafeWeekDay> weekDays;
	
	@JsonProperty("description")
	private String description;
	
	@JsonIgnore
	private String link;
	
	@JsonIgnore
	private String guid;
	
	@JsonProperty("title")
	private String restaurantName;
	
	public List<UniCafeWeekDay> getWeekDays() {
		return weekDays;
	}
	
	public void setWeekDays(List<UniCafeWeekDay> weekDays) {
		this.weekDays = weekDays;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	@Override
	public String toString() {
		return "UniCafeWeek [weekDays=" + weekDays + ", description=" + description + ", link=" + link + ", guid="
				+ guid + ", restaurantName=" + restaurantName + "]";
	}
	


	

}
