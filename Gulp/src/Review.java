

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setAttribute("date", "mm/dd/yyyy");
		request.setAttribute("description", "Enter Description");
		request.setAttribute("action","Review");
		getServletContext().getRequestDispatcher("/Review.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurant = request.getParameter("restaurant");
		int rating = Integer.parseInt(request.getParameter("optradio"));
		String date = request.getParameter("date");
		date = date.replace("-", "/");
		User user = (User)request.getSession().getAttribute("User");
		String description = request.getParameter("desc");
		postReview(restaurant, user.getUser_id(),rating,date, description);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
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
	
	protected void postReview(String rest, int user_id, int rating, String date, String desc){
		
		int restId = getRestId(rest);
		String sql = "insert into review(user_id,restaurant_id,review_date,review_des,rating)"+
					"values("+user_id+","+restId+","+"to_Date('"+date+"','yyyy/mm/dd'),'"+desc+"',"+rating+")";
		//System.out.println(sql);
		try {
			DBQuery.updateDB(sql);
			updateNumReviews(restId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected int getRestId(String restName){
		String sql = "select restaurant_id from restaurants where restaurant_name ='" + restName +"'";
		try {
			ResultSet result = DBQuery.getFromDB(sql);
			if(result.next()){
				return result.getInt("restaurant_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	protected void updateNumReviews(int restId){
		String sql = "update restaurants set num_rating = ((select num_rating from restaurants where restaurant_id = "+ 
				restId+") + 1) where restaurant_id = "+ restId;
		try {
			DBQuery.updateDB(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
