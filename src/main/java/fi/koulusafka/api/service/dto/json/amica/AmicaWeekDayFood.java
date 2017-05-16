package fi.koulusafka.api.service.dto.json.amica;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmicaWeekDayFood {
	
	@JsonProperty("SortOrder")
	private int sortOrder;
	
	@JsonProperty("Name")
	private String name; 
	
	@JsonProperty("Price")
	private String price;
	
	@JsonProperty("Components")
	private List<String> components;

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	@Override
	public String toString() {
		return "AmicaWeekDayFood [sortOrder=" + sortOrder + ", name=" + name + ", price=" + price + ", components="
				+ components + "]";
	}
	
	
	
	

}
