package fi.koulusafka.api.service.dto.json.unicafe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UniCafeWeekDayFood {

	@JsonProperty("name_en")
	private String foodNameEn;
	
	@JsonIgnore
	private String nutritional_info_en;
	
	@JsonProperty("description")
	private String description;
	
	@JsonIgnore
	private String price;
	
	@JsonIgnore
	private String nutritional_info_sv;
	
	@JsonProperty("name")
	private String foodNameFi;
	
	@JsonIgnore
	private String description_en;
	
	@JsonProperty("nutritional_info")
	private String nutritional_info;
	
	@JsonProperty("name_sv")
	private String foodNameSv;
	
	@JsonIgnore
	private String description_sv;
	
	@JsonProperty("allergies")
	List<String> properties;
	
	
	public String getFoodNameEn() {
		return foodNameEn;
	}
		
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription_en() {
		return description_en;
	}
	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}
	public void setFoodNameEn(String foodNameEn) {
		this.foodNameEn = foodNameEn;
	}
	public String getNutritional_info_en() {
		return nutritional_info_en;
	}
	public void setNutritional_info_en(String nutritional_info_en) {
		this.nutritional_info_en = nutritional_info_en;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNutritional_info_sv() {
		return nutritional_info_sv;
	}
	public void setNutritional_info_sv(String nutritional_info_sv) {
		this.nutritional_info_sv = nutritional_info_sv;
	}
	public String getFoodNameFi() {
		return foodNameFi;
	}
	public void setFoodNameFi(String foodNameFi) {
		this.foodNameFi = foodNameFi;
	}
	public String getNutritional_info() {
		return nutritional_info;
	}
	public void setNutritional_info(String nutritional_info) {
		this.nutritional_info = nutritional_info;
	}
	public String getFoodNameSv() {
		return foodNameSv;
	}
	public void setFoodNameSv(String foodNameSv) {
		this.foodNameSv = foodNameSv;
	}
	public String getDescription_sv() {
		return description_sv;
	}
	public void setDescription_sv(String description_sv) {
		this.description_sv = description_sv;
	}
	public List<String> getProperties() {
		return properties;
	}
	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "UniCafeWeekDayFood [foodNameEn=" + foodNameEn + ", nutritional_info_en=" + nutritional_info_en
				+ ", description=" + description + ", price=" + price + ", nutritional_info_sv=" + nutritional_info_sv
				+ ", foodNameFi=" + foodNameFi + ", description_en=" + description_en + ", nutritional_info="
				+ nutritional_info + ", foodNameSv=" + foodNameSv + ", description_sv=" + description_sv
				+ ", properties=" + properties + "]";
	}
	
	
	
}
