package fi.koulusafka.api.service.dto.json.sodexo;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SodexoWeek {

	
	@JsonProperty("meta")
	private Map<String,String> meta;
	
	@JsonProperty("menus")
	private Map<String, List<SodexoWeekDay>> weekDay;
	
	public Map<String, String> getMeta() {
		return meta;
	}
	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}
	public Map<String, List<SodexoWeekDay>> getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Map<String, List<SodexoWeekDay>> weekDay) {
		this.weekDay = weekDay;
	}
	@Override
	public String toString() {
		return "SodexoWeek [meta=" + meta + ", weekDay=" + weekDay + "]";
	}
	
	
	

}
