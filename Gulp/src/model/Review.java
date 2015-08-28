package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the REVIEW database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReviewPK id;

	private BigDecimal rating;

	@Temporal(TemporalType.DATE)
	@Column(name="REVIEW_DATE")
	private Date reviewDate;

	@Column(name="REVIEW_DES")
	private String reviewDes;

	//bi-directional many-to-one association to GulpData
	@ManyToOne
	@JoinColumn(name="RESTAURANT_ID")
	private Restaurants restaurant;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Userprofile userprofile;

	public Review() {
	}

	public ReviewPK getId() {
		return this.id;
	}

	public void setId(ReviewPK id) {
		this.id = id;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewDes() {
		return this.reviewDes;
	}

	public void setReviewDes(String reviewDes) {
		this.reviewDes = reviewDes;
	}

	public Restaurants getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public Userprofile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(Userprofile userprofile) {
		this.userprofile = userprofile;
	}

}