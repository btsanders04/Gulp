

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResturantsReviews
 */
@WebServlet("/RestaurantReviews")
public class RestaurantReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String reviews ="";
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
		reviews="";
		String id = request.getParameter("Restaurantid");
	//	System.out.println(""+id);
		try {
			DBQuery.openConnection();
			String sql = "select * from review where restaurant_id="+Integer.parseInt(id);
		reviews+="<div class=\"container\">";
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next())
			{
				String sql2 = "select user_name from userprofile where user_id="+result.getString("USER_ID");
				ResultSet result2 = DBQuery.getFromDB(sql2);
				result2.next();
				reviews+=" <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+result2.getString("user_name")+"</div>"+
			     " <div class=\"panel-body\"><p > Date: "+result.getDate("REVIEW_DATE")
						+"</p><p>  Rating: "+result.getString("RATING")+"</p><p>"
			     +result.getString("REVIEW_DES")+"</p></div></div>";
			
				}
			reviews+="</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("reviews", reviews);
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
