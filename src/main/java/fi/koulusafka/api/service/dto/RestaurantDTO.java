package fi.koulusafka.api.service.dto;

import fi.koulusafka.api.domain.enumeration.Brand;

public class RestaurantDTO {
	
	private Long id;
	private Brand brand;
	private String full_name;
	private String name;


	
	
	public Long getId() {
		return id;
	}
	public RestaurantDTO setId(Long id) {
		this.id = id;
		return this;
	}
	public Brand getBrand() {
		return brand;
	}
	public RestaurantDTO setBrand(Brand brand) {
		this.brand = brand;
		return this;
	}
	public String getFullName() {
		return full_name;
	}
	public RestaurantDTO setFullName(String full_name) {
		this.full_name = full_name;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public RestaurantDTO setName(String name) {
		this.name = name;
		return this;
	}
	@Override
	public String toString() {
		return "RestaurantDTO [id=" + id + ", brand=" + brand + ", full_name=" + full_name + ", name=" + name + "]";
	}

	
	

	
	
	
}
