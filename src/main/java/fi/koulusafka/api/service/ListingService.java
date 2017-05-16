package fi.koulusafka.api.service;

import fi.koulusafka.api.domain.Listing;
import fi.koulusafka.api.repository.ListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Listing.
 */
@Service
@Transactional
public class ListingService {

    private final Logger log = LoggerFactory.getLogger(ListingService.class);
    
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    /**
     * Save a listing.
     *
     * @param listing the entity to save
     * @return the persisted entity
     */
    public Listing save(Listing listing) {
        log.debug("Request to save Listing : {}", listing);
        Listing result = listingRepository.save(listing);
        return result;
    }

    /**
     *  Get all the listings.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Listing> findAll() {
        log.debug("Request to get all Listings");
        List<Listing> result = listingRepository.findAllWithEagerRelationships();

        return result;
    }

    /**
     *  Get one listing by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Listing findOne(Long id) {
        log.debug("Request to get Listing : {}", id);
        Listing listing = listingRepository.findOneWithEagerRelationships(id);
        return listing;
    }

    /**
     *  Delete the  listing by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Listing : {}", id);
        listingRepository.delete(id);
    }
}
