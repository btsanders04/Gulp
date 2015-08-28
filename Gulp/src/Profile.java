import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String profiles = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		profiles = "";
		model.Userprofile user = (model.Userprofile) session
				.getAttribute("User");
		long id = user.getUserId();
		profiles += "<div class=\"container\"><div class=\"item  col-xs-4 col-lg-4\">  <div class=\"panel panel-primary\"><div class=\"panel-heading\">"
				+ user.getUserId()
				+ "</div><div class=\"panel-body\"> <p class=\"group inner list-group-item-text\">"
				+ user.getUserName()
				+ "</p> <p class=\"group inner list-group-item-text\"> "
				+ user.getUserEmail()
				+ "</p> <p class=\"group inner list-group-item-text\"> "
				+ user.getUserZipcode()
				+ "</p></div></div></div></div><div class=\"container\">";

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select r from Review r where r.userprofile = :user";
		TypedQuery<model.Review> reviews = em.createQuery(sql,
				model.Review.class);
		reviews.setParameter("user", user);
		try {
			List<model.Review> reviewList = reviews.getResultList();
			for (model.Review r : reviewList) {
				profiles += " <div class=\"panel panel-primary\"><div class=\"panel-heading\">"
						+ r.getRestaurant().getRestaurantName()
						+ "</div>"
						+ " <div class=\"panel-body\"><p > Date: "
						+ r.getReviewDate()
						+ "</p><p>  Rating: "
						+ r.getRating()
						+ "</p><p>"
						+ r.getReviewDes()
						+ "</p><div class=\"col-xs-12 col-md-6\"><a class=\"btn btn-primary\" href=\"EditReview?restaurant="
						+ r.getRestaurant().getRestaurantId()
						+ "\"> Edit Review </a></div></div></div>";

			}
			profiles += "</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("profiles", profiles);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
