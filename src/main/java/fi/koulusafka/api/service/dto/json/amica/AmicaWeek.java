package fi.koulusafka.api.service.dto.json.amica;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmicaWeek {
	
	
	@JsonProperty("RestaurantName")
	private String restaurantName;
	
	@JsonProperty("RestaurantUrl")
	private String restaurantUrl;
	
	@JsonProperty("PriceHeader")
	private String priceHeader;
	
	@JsonProperty("Footer")
	private String footer;
	
	@JsonProperty("MenusForDays")
	private List<AmicaWeekDay> menusForDays;
	
	@JsonProperty("ErrorText")
	private String errorText;
	
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantUrl() {
		return restaurantUrl;
	}
	public void setRestaurantUrl(String restaurantUrl) {
		this.restaurantUrl = restaurantUrl;
	}
	public String getPriceHeader() {
		return priceHeader;
	}
	public void setPriceHeader(String priceHeader) {
		this.priceHeader = priceHeader;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public List<AmicaWeekDay> getMenusForDays() {
		return menusForDays;
	}
	public void setMenusForDays(List<AmicaWeekDay> menusForDays) {
		this.menusForDays = menusForDays;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	@Override
	public String toString() {
		return "AmicaWeek [restaurantName=" + restaurantName + ", restaurantUrl=" + restaurantUrl + ", priceHeader="
				+ priceHeader + ", footer=" + footer + ", menusForDays=" + menusForDays + ", errorText=" + errorText
				+ "]";
	}
	
	

}
