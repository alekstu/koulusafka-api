package fi.koulusafka.api.repository;

import fi.koulusafka.api.domain.Listing;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Listing entity.
 */
@SuppressWarnings("unused")
public interface ListingRepository extends JpaRepository<Listing,Long> {

    @Query("select distinct listing from Listing listing left join fetch listing.restaurants")
    List<Listing> findAllWithEagerRelationships();

    @Query("select listing from Listing listing left join fetch listing.restaurants where listing.id =:id")
    Listing findOneWithEagerRelationships(@Param("id") Long id);

}
