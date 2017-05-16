package fi.koulusafka.api.web.rest;

import fi.koulusafka.api.KoulusafkaApiApp;

import fi.koulusafka.api.domain.Restaurant;
import fi.koulusafka.api.repository.RestaurantRepository;
import fi.koulusafka.api.service.RestaurantService;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static fi.koulusafka.api.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fi.koulusafka.api.domain.enumeration.Brand;
/**
 * Test class for the RestaurantResource REST controller.
 *
 * @see RestaurantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KoulusafkaApiApp.class)
public class RestaurantResourceIntTest {

    private static final Brand DEFAULT_BRAND = Brand.Amica;
    private static final Brand UPDATED_BRAND = Brand.Sodexo;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESTAURANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESTAURANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POST_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_POST_OFFICE = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE_URL = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE_URL = "BBBBBBBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Integer DEFAULT_HITS = 1;
    private static final Integer UPDATED_HITS = 2;

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final ZonedDateTime DEFAULT_DISABLED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DISABLED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRestaurantMockMvc;

    private Restaurant restaurant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RestaurantResource restaurantResource = new RestaurantResource(restaurantService);
        this.restRestaurantMockMvc = MockMvcBuilders.standaloneSetup(restaurantResource)
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
    public static Restaurant createEntity(EntityManager em) {
        Restaurant restaurant = new Restaurant()
                .brand(DEFAULT_BRAND)
                .name(DEFAULT_NAME)
                .restaurantId(DEFAULT_RESTAURANT_ID)
                .address(DEFAULT_ADDRESS)
                .postalCode(DEFAULT_POSTAL_CODE)
                .postOffice(DEFAULT_POST_OFFICE)
                .websiteUrl(DEFAULT_WEBSITE_URL)
                .latitude(DEFAULT_LATITUDE)
                .longitude(DEFAULT_LONGITUDE)
                .hits(DEFAULT_HITS)
                .enabled(DEFAULT_ENABLED)
                .disabledDate(DEFAULT_DISABLED_DATE);
        return restaurant;
    }

    @Before
    public void initTest() {
        restaurant = createEntity(em);
    }

    @Test
    @Transactional
    public void getNonExistingRestaurant() throws Exception {
        // Get the restaurant
        restRestaurantMockMvc.perform(get("/api/restaurants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }


    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Restaurant.class);
    }
}
