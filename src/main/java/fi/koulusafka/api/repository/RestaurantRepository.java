package fi.koulusafka.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.koulusafka.api.domain.Restaurant;
import fi.koulusafka.api.domain.enumeration.Brand;
import fi.koulusafka.api.service.dto.RestaurantDTO;

/**
 * Spring Data JPA repository for the Restaurant entity.
 */
@SuppressWarnings("unused")
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
	
	Restaurant findByName(String name);

	Restaurant findByWebsiteUrlIgnoreCase(String websiteUrl);
	
	Optional<List<Restaurant>> findByBrand(Brand brand);
	
	Optional<List<Restaurant>> findByPostOfficeIgnoreCase(String postOffice);

	Optional<List<Restaurant>> findByBrandAndPostOfficeIgnoreCase(Brand brand, String postOffice);

	Optional<List<Restaurant>> findByPostalCode(String postalCode);



}
