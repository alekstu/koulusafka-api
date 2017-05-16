package fi.koulusafka.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import fi.koulusafka.api.domain.enumeration.Brand;

/**
 * A Restaurant.
 */
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private Brand brand;

    @Column(name = "name")
    private String name;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "post_office")
    private String postOffice;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "hits")
    private Integer hits;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "disabled_date")
    private ZonedDateTime disabledDate;

    @ManyToMany(mappedBy = "restaurants")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Listing> listings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public Restaurant brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public Restaurant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public Restaurant restaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAddress() {
        return address;
    }

    public Restaurant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Restaurant postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public Restaurant postOffice(String postOffice) {
        this.postOffice = postOffice;
        return this;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public Restaurant websiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
        return this;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Restaurant latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Restaurant longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getHits() {
        return hits;
    }

    public Restaurant hits(Integer hits) {
        this.hits = hits;
        return this;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public Restaurant enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ZonedDateTime getDisabledDate() {
        return disabledDate;
    }

    public Restaurant disabledDate(ZonedDateTime disabledDate) {
        this.disabledDate = disabledDate;
        return this;
    }

    public void setDisabledDate(ZonedDateTime disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Set<Listing> getListings() {
        return listings;
    }

    public Restaurant listings(Set<Listing> listings) {
        this.listings = listings;
        return this;
    }

    public Restaurant addListing(Listing listing) {
        this.listings.add(listing);
        listing.getRestaurants().add(this);
        return this;
    }

    public Restaurant removeListing(Listing listing) {
        this.listings.remove(listing);
        listing.getRestaurants().remove(this);
        return this;
    }

    public void setListings(Set<Listing> listings) {
        this.listings = listings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant restaurant = (Restaurant) o;
        if (restaurant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, restaurant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + id +
            ", brand='" + brand + "'" +
            ", name='" + name + "'" +
            ", restaurantId='" + restaurantId + "'" +
            ", address='" + address + "'" +
            ", postalCode='" + postalCode + "'" +
            ", postOffice='" + postOffice + "'" +
            ", websiteUrl='" + websiteUrl + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            ", hits='" + hits + "'" +
            ", enabled='" + enabled + "'" +
            ", disabledDate='" + disabledDate + "'" +
            '}';
    }
}
