package fi.koulusafka.api.web.rest;

import fi.koulusafka.api.KoulusafkaApiApp;

import fi.koulusafka.api.domain.Listing;
import fi.koulusafka.api.repository.ListingRepository;
import fi.koulusafka.api.service.ListingService;
import fi.koulusafka.api.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ListingResource REST controller.
 *
 * @see ListingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KoulusafkaApiApp.class)
public class ListingResourceIntTest {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_HITS = 1;
    private static final Integer UPDATED_HITS = 2;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingService listingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restListingMockMvc;

    private Listing listing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ListingResource listingResource = new ListingResource(listingService);
        this.restListingMockMvc = MockMvcBuilders.standaloneSetup(listingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Listing createEntity(EntityManager em) {
        Listing listing = new Listing()
                .url(DEFAULT_URL)
                .description(DEFAULT_DESCRIPTION)
                .hits(DEFAULT_HITS);
        return listing;
    }

    @Before
    public void initTest() {
        listing = createEntity(em);
    }

    @Test
    @Transactional
    public void createListing() throws Exception {
        int databaseSizeBeforeCreate = listingRepository.findAll().size();

        // Create the Listing

        restListingMockMvc.perform(post("/api/listings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listing)))
            .andExpect(status().isCreated());

        // Validate the Listing in the database
        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeCreate + 1);
        Listing testListing = listingList.get(listingList.size() - 1);
        assertThat(testListing.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testListing.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListing.getHits()).isEqualTo(DEFAULT_HITS);
    }

    @Test
    @Transactional
    public void createListingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listingRepository.findAll().size();

        // Create the Listing with an existing ID
        Listing existingListing = new Listing();
        existingListing.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListingMockMvc.perform(post("/api/listings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingListing)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = listingRepository.findAll().size();
        // set the field null
        listing.setUrl(null);

        // Create the Listing, which fails.

        restListingMockMvc.perform(post("/api/listings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listing)))
            .andExpect(status().isBadRequest());

        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListings() throws Exception {
        // Initialize the database
        listingRepository.saveAndFlush(listing);

        // Get all the listingList
        restListingMockMvc.perform(get("/api/listings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listing.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].hits").value(hasItem(DEFAULT_HITS)));
    }

    @Test
    @Transactional
    public void getListing() throws Exception {
        // Initialize the database
        listingRepository.saveAndFlush(listing);

        // Get the listing
        restListingMockMvc.perform(get("/api/listings/{id}", listing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listing.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.hits").value(DEFAULT_HITS));
    }

    @Test
    @Transactional
    public void getNonExistingListing() throws Exception {
        // Get the listing
        restListingMockMvc.perform(get("/api/listings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListing() throws Exception {
        // Initialize the database
        listingService.save(listing);

        int databaseSizeBeforeUpdate = listingRepository.findAll().size();

        // Update the listing
        Listing updatedListing = listingRepository.findOne(listing.getId());
        updatedListing
                .url(UPDATED_URL)
                .description(UPDATED_DESCRIPTION)
                .hits(UPDATED_HITS);

        restListingMockMvc.perform(put("/api/listings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedListing)))
            .andExpect(status().isOk());

        // Validate the Listing in the database
        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeUpdate);
        Listing testListing = listingList.get(listingList.size() - 1);
        assertThat(testListing.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testListing.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListing.getHits()).isEqualTo(UPDATED_HITS);
    }

    @Test
    @Transactional
    public void updateNonExistingListing() throws Exception {
        int databaseSizeBeforeUpdate = listingRepository.findAll().size();

        // Create the Listing

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restListingMockMvc.perform(put("/api/listings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listing)))
            .andExpect(status().isCreated());

        // Validate the Listing in the database
        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteListing() throws Exception {
        // Initialize the database
        listingService.save(listing);

        int databaseSizeBeforeDelete = listingRepository.findAll().size();

        // Get the listing
        restListingMockMvc.perform(delete("/api/listings/{id}", listing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Listing> listingList = listingRepository.findAll();
        assertThat(listingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Listing.class);
    }
}
