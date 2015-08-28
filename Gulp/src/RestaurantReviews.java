

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class ResturantsReviews
 */
@WebServlet("/RestaurantReviews")
public class RestaurantReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantReviews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewString="";
		Long id = Long.parseLong(request.getParameter("Restaurantid"));
	//	System.out.println(""+id);
		try {
			reviewString+="<div class=\"container\">";
			String sql = "select r from Review r where r.restaurant.restaurantId = :restId";
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			;
			List<model.Review> reviews = em.createQuery(sql,model.Review.class).setParameter("restId", id).getResultList();
			for(model.Review r : reviews){
				reviewString+=" <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+r.getUserprofile().getUserName()+"</div>"+
			     " <div class=\"panel-body\"><p > Date: "+r.getReviewDate()
						+"</p><p>  Rating: "+r.getRating()+"</p><p>"
			     +r.getReviewDes()+"</p></div></div>";
				}
			reviewString+="</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("reviews", reviewString);
		getServletContext().getRequestDispatcher("/RestaurantReviews.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
