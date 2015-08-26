

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
			
			String sql = "select * from restaurants";
			restaurants+=" <div class=\"container\"><div  class=\"row list-group\">";
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next())
			{
				updateAverage(result.getInt("restaurant_id"));
				restaurants+="<div class=\"item  col-xs-4 col-lg-4\"> <div class=\"thumbnail\"> <div class=\"caption\">"+
		                  " <h4 class=\"group inner list-group-item-heading\">"+
	                       result.getString("RESTAURANT_NAME")+"</h4> <p class=\"group inner list-group-item-text\">"+
	                       result.getString("RESTAURANT_DES")+"</p> "+
	                       "<p class=\"group inner list-group-item-text\"> <b>Address: </b>"+
	                       result.getString("RESTAURANT_ADD")+"</p>  <p class=\"group inner list-group-item-text\"><b>Rating: </b>"+
	                       result.getString("AVG_RATING")+"</p> <p class=\"group inner list-group-item-text\"> <b>Number of Rating: </b>"+
	                       result.getString("NUM_RATING")+"</p> "+
	                       "<div class=\"row\"><div class=\"col-xs-12 col-md-6\"><a class=\"btn btn-primary\" href=\"RestaurantReviews?Restaurantid="+result.getString("RESTAURANT_ID")+"\"> Reviews </a></div>"+
	                       "<div class=\"col-md-4\"><a class=\"btn btn-primary\" href=\"EditRestaurant?Restaurantid="+result.getString("RESTAURANT_ID")+"&&Restaurantname="+ result.getString("RESTAURANT_NAME")+"&&Restaurantadd="+  result.getString("RESTAURANT_ADD")+"&&Restaurantde="+  result.getString("RESTAURANT_DES")+"\"> Edit </a> </div> </div>"+"  </div>      </div></div>";          
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
