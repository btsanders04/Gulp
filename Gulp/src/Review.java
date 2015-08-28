import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;

/**
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBQuery.openConnection();
		request.setAttribute("restaurants", restaurantList());
		request.setAttribute("date", "mm/dd/yyyy");
		request.setAttribute("description", "Enter Description");
		request.setAttribute("action", "Review");
		getServletContext().getRequestDispatcher("/Review.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String restaurant = request.getParameter("restaurant");
		BigDecimal rating = new BigDecimal(request.getParameter("optradio"));
		System.out.println(request.getParameter("date"));
		Date date=new Date();
		model.Userprofile user = (model.Userprofile) request.getSession()
				.getAttribute("User");
		String description = request.getParameter("desc");
		postReview(restaurant, user, rating, date, description);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}

	protected String restaurantList() {
		String restaurants = "";

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<model.Restaurants> restQuery = em.createQuery(
				"SELECT g FROM Restaurants g", model.Restaurants.class);
		try {
			List<model.Restaurants> restList = restQuery.getResultList();
			for (model.Restaurants r : restList) {
				restaurants += "<option>" + r.getRestaurantName() + "</option>";
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}

		return restaurants;
	}

	protected void postReview(String rest, model.Userprofile user, BigDecimal rating,
			Date date, String desc) {

		model.Restaurants restaurant = getRestaurant(rest);
		model.Review review = new model.Review();
		review.setRestaurant(restaurant);
		review.setUserprofile(user);
		review.setRating(rating);
		review.setReviewDate(date);
		review.setReviewDes(desc);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(review);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	protected model.Restaurants getRestaurant(String restName) {
		String sql = "select g from Restaurants g where g.restaurantName = :restName";
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<model.Restaurants> restQuery = em.createQuery(sql,
				model.Restaurants.class);
		restQuery.setParameter("restName", restName);
		try {
			return restQuery.getSingleResult();	 
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return null;
	}

	protected void updateNumReviews(long restId) {
		// String sql =
		// "update restaurants set num_rating = ((select num_rating from restaurants where restaurant_id = "
		// + restId + ") + 1) where restaurant_id = " + restId;
		String sql = "UPDATE Restaurants g SET g.numRating = ((select g.numRating FROM restaurants g where"
				+ "g.restaurantId = :restId) + 1) where g.restaurantId = :restId";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<model.Restaurants> restQuery = em.createQuery(sql,
				model.Restaurants.class);
		restQuery.setParameter("restId", restId);
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			restQuery.executeUpdate();
			trans.commit();

		} catch (Exception e) {
			trans.rollback();
			System.out.println(e);
		} finally {
			em.close();
		}

	}

}
