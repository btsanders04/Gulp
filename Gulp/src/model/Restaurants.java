package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the RESTAURANTS database table.
 * 
 */
@Entity
@Table(name="RESTAURANTS")
@NamedQuery(name="Restaurants.findAll", query="SELECT g FROM Restaurants g")
public class Restaurants implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RESTAURANT_ID")
	private long restaurantId;

	@Column(name="AVG_RATING")
	private BigDecimal avgRating;

	@Column(name="NUM_RATING")
	private BigDecimal numRating;

	@Column(name="RESTAURANT_ADD")
	private String restaurantAdd;

	@Column(name="RESTAURANT_DES")
	private String restaurantDes;

	@Column(name="RESTAURANT_NAME")
	private String restaurantName;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="restaurant")
	private List<Review> reviews;

	public Restaurants() {
	}

	public long getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public BigDecimal getAvgRating() {
		return this.avgRating;
	}

	public void setAvgRating(BigDecimal avgRating) {
		this.avgRating = avgRating;
	}

	public BigDecimal getNumRating() {
		return this.numRating;
	}

	public void setNumRating(BigDecimal numRating) {
		this.numRating = numRating;
	}

	public String getRestaurantAdd() {
		return this.restaurantAdd;
	}

	public void setRestaurantAdd(String restaurantAdd) {
		this.restaurantAdd = restaurantAdd;
	}

	public String getRestaurantDes() {
		return this.restaurantDes;
	}

	public void setRestaurantDes(String restaurantDes) {
		this.restaurantDes = restaurantDes;
	}

	public String getRestaurantName() {
		return this.restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setRestaurant(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setRestaurant(null);

		return review;
	}

}