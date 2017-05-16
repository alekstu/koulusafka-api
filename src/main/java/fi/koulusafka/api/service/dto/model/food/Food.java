package fi.koulusafka.api.service.dto.model.food;

public class Food {
	
	private String name;
	private String properties;
	private String description;
	private String price;
	private String extra;
	
	
	public String getName() {
		return name;
	}
	public Food setName(String name) {
		this.name = name;
		return this;
	}
	

	public String getProperties() {
		return properties;
	}
	public Food setProperties(String properties) {
		this.properties = properties;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Food setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getPrice() {
		return price;
	}
	public Food setPrice(String price) {
		this.price = price;
		return this;
	}
	public String getExtra() {
		return extra;
	}
	public Food setExtra(String extra) {
		this.extra = extra;
		return this;
	}
	@Override
	public String toString() {
		return "Food [name=" + name + ", properties=" + properties + ", description=" + description + ", price=" + price
				+ ", extra=" + extra + "]";
	}
	
	
	

}
