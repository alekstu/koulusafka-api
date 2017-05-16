package fi.koulusafka.api.service.dto.json.sodexo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SodexoWeekDay {
	
		
		@JsonProperty("title_fi")
		private String foodNameFi;
		
		@JsonProperty("title_en")
		private String foodNameEn;
		
		@JsonProperty("price")
		private String price;
		
		@JsonProperty("category")
		private String category;
		
		
		@JsonProperty("properties")
		private String properties;
		@JsonProperty("desc_fi")
		private String descriptionFin;
		
		@JsonProperty("desc_en")
		private String descriptionEn;
		
		@JsonProperty("desc_se")
		private String descriptionSe;
		
		
		
		public String getFoodNameFi() {
			return foodNameFi;
		}
		public void setFoodNameFi(String foodNameFi) {
			this.foodNameFi = foodNameFi;
		}
		public String getFoodNameEn() {
			return foodNameEn;
		}
		public void setFoodNameEn(String foodNameEn) {
			this.foodNameEn = foodNameEn;
		}
		public String getPrice() {
			return price;
		}
		
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getProperties() {
			return properties;
		}
		public void setProperties(String properties) {
			this.properties = properties;
		}
		public String getDescriptionFin() {
			return descriptionFin;
		}
		public void setDescriptionFin(String descriptionFin) {
			this.descriptionFin = descriptionFin;
		}
		public String getDescriptionEn() {
			return descriptionEn;
		}
		public void setDescriptionEn(String descriptionEn) {
			this.descriptionEn = descriptionEn;
		}
		public String getDescriptionSe() {
			return descriptionSe;
		}
		public void setDescriptionSe(String descriptionSe) {
			this.descriptionSe = descriptionSe;
		}
		
		@Override
		public String toString() {
			return "SodexoWeekDayJson [foodNameFi=" + foodNameFi + ", foodNameEn=" + foodNameEn + ", price=" + price
					+ ", category=" + category + ", properties=" + properties + ", descriptionFin=" + descriptionFin
					+ ", descriptionEn=" + descriptionEn + ", descriptionSe=" + descriptionSe + "]";
		}
		
		

}
