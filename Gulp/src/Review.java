

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBQuery.openConnection();
		request.setAttribute("restaurants", restaurantList());
		getServletContext().getRequestDispatcher("/Review.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurant = request.getParameter("restaurant");
		int rating = Integer.parseInt(request.getParameter("optradio"));
		System.out.println(rating);
		String description = request.getParameter("desc");
		
		
	}
	
	protected String restaurantList(){
		String restaurants="";
		String sql = "select restaurant_name from restaurants";
		try {
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next()){
				restaurants+="<option>"+result.getString("restaurant_name")+"</option>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurants;
	}
	
	protected void postReview(String rest, int rating, String desc){
		
		
		
		//String sql = "insert into "
	}
	
	protected void getRestId(String restName){
	//	String sql = "select rest"
	}

}
