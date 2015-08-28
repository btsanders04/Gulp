package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REVIEW database table.
 * 
 */
@Embeddable
public class ReviewPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RESTAURANT_ID", insertable=false, updatable=false)
	private long restaurantId;

	@Column(name="USER_ID", insertable=false, updatable=false)
	private long userId;

	public ReviewPK() {
	}
	public long getRestaurantId() {
		return this.restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public long getUserId() {
		return this.userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReviewPK)) {
			return false;
		}
		ReviewPK castOther = (ReviewPK)other;
		return 
			(this.restaurantId == castOther.restaurantId)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.restaurantId ^ (this.restaurantId >>> 32)));
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		
		return hash;
	}
}