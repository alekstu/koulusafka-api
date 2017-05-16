package fi.koulusafka.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Listing.
 */
@Entity
@Table(name = "listing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "url", length = 30, nullable = false)
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "hits")
    private Integer hits;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "listing_restaurant",
               joinColumns = @JoinColumn(name="listings_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="restaurants_id", referencedColumnName="id"))
    private Set<Restaurant> restaurants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Listing url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public Listing description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHits() {
        return hits;
    }

    public Listing hits(Integer hits) {
        this.hits = hits;
        return this;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Listing restaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
        return this;
    }

    public Listing addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        restaurant.getListings().add(this);
        return this;
    }

    public Listing removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.getListings().remove(this);
        return this;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Listing listing = (Listing) o;
        if (listing.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, listing.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Listing{" +
            "id=" + id +
            ", url='" + url + "'" +
            ", description='" + description + "'" +
            ", hits='" + hits + "'" +
            '}';
    }
}
