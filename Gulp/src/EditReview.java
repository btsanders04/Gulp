

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditReview
 */
@WebServlet("/EditReview")
public class EditReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String[] details;
    private int restId;
    private User user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restId=Integer.parseInt(request.getParameter("restaurant"));
		user = (User)request.getSession().getAttribute("User");
		details=reviewDetails(restId,user.getUser_id());
		request.setAttribute("restaurants","<option>"+details[0]+"</option>");
		request.setAttribute("description", details[1]);
		request.setAttribute("action", "EditReview");
		getServletContext().getRequestDispatcher("/Review.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=details[0];
		String desc=details[1];
		int rating = Integer.parseInt(request.getParameter("optradio"));
		if(!request.getParameter("date").equals(""))
			date=request.getParameter("date").replace("-", "/");
		System.out.println(date);
		if(!request.getParameter("desc").equals(""))
			desc=request.getParameter("desc");
		submitReviewEdit(date,desc,rating,restId,user.getUser_id());
		getServletContext().getRequestDispatcher("/Profile").forward(request,
				response);
	}

	protected String[] reviewDetails(int restId, int userId){
		String sql = "select review_des, restaurant_name from review join restaurants on review.restaurant_id = "+restId +
				" and review.user_id = " + userId +" and restaurants.restaurant_id="+restId;
		System.out.println(sql);
		String[] reviewDetails = new String[2];
		try {
			ResultSet result = DBQuery.getFromDB(sql);
			if(result.next()){
				reviewDetails[0]=result.getString("restaurant_name");
				System.out.println(reviewDetails[0]);
				reviewDetails[1]=result.getString("review_des");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewDetails;
	}
	
	protected void submitReviewEdit(String date, String desc, int rating, int restId, int userId){
		String sql = "update review set review_date=to_Date('"+date+"','yyyy/mm/dd'), review_des='"+desc+
				"', rating="+rating+
				" where user_id=" + userId + " and restaurant_id = "+restId;
		try {
			DBQuery.updateDB(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
