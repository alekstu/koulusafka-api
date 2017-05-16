package fi.koulusafka.api.service.dto.json.amica;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmicaWeekDay {

	
	@JsonProperty("Date")
	private Date date;
	
	@JsonProperty("LunchTime")
	private String lunchTime;
	
	@JsonProperty("SetMenus")
	private List<AmicaWeekDayFood> setMenus;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLunchTime() {
		return lunchTime;
	}
	public void setLunchTime(String lunchTime) {
		this.lunchTime = lunchTime;
	}
	public List<AmicaWeekDayFood> getSetMenus() {
		return setMenus;
	}
	public void setSetMenus(List<AmicaWeekDayFood> setMenus) {
		this.setMenus = setMenus;
	}
	@Override
	public String toString() {
		return "AmicaWeekDay [date=" + date + ", lunchTime=" + lunchTime + ", setMenus=" + setMenus + "]";
	}
	
	
}
