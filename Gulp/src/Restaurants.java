

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Restaraunts
 */
@WebServlet("/Restaurants")
public class Restaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String restaurants="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restaurants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restaurants="";
		try {
			DBQuery.openConnection();
			
			String sql = "select * from restaurants order by avg_rating desc";
			restaurants+=" <div class=\"container\"><div class=\"row\">";
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next())
			{
				updateAverage(result.getInt("restaurant_id"));
				restaurants+="<div class=\"item  col-sm-6 col-sm-6\"> <div class=\"thumbnail\"> <div class=\"caption\">"+
		                  " <h4>"+
	                       result.getString("RESTAURANT_NAME")+"</h4> <p>"+
	                       result.getString("RESTAURANT_DES")+"</p> "+
	                       "<p> <b>Address: </b>"+
	                       result.getString("RESTAURANT_ADD")+"</p>  <p><b>Rating: </b>"+
	                       result.getString("AVG_RATING")+"</p> <p> <b>Number of Rating: </b>"+
	                       result.getString("NUM_RATING")+"</p> "+
	                       "<a class=\"btn btn-primary\" href=\"RestaurantReviews?Restaurantid="+result.getString("RESTAURANT_ID")+"\"> Reviews </a>"+
	                       "<a class=\"btn btn-primary\" href=\"EditRestaurant?Restaurantid="+result.getString("RESTAURANT_ID")+"&&Restaurantname="+ result.getString("RESTAURANT_NAME")+"&&Restaurantadd="+  result.getString("RESTAURANT_ADD")+"&&Restaurantde="+  result.getString("RESTAURANT_DES")+"\"> Edit </a></div></div></div>";          
			}

               
		   
			restaurants+="</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("restaurants", restaurants);
		getServletContext().getRequestDispatcher("/Restaurants.jsp").forward(request,
				response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void updateAverage(int rest_id){
		String sql = 
				"update restaurants set avg_rating = (select avg(rating) from review "+
						"where review.restaurant_id =" +rest_id + ") where restaurants.restaurant_id= " + rest_id ;
	//	System.out.println(sql);
		try {
			DBQuery.updateDB(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
