

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		profiles="";
		User user = (User) session.getAttribute("User");
		Integer id = user.getUser_id();
	
	try {
			DBQuery.openConnection();
			String sql2 = "select * from userprofile where user_id="+id;
			System.out.println(sql2);
			ResultSet result2 = DBQuery.getFromDB(sql2);
			result2.next();
			profiles+="<div class=\"container\"><div class=\"item  col-xs-4 col-lg-4\">  <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+result2.getString("USER_ID") 
					+"</div><div class=\"panel-body\"> <p class=\"group inner list-group-item-text\">"+result2.getString("USER_NAME") 
						+"</p> <p class=\"group inner list-group-item-text\"> "+result2.getString("USER_EMAIL") 
						+"</p> <p class=\"group inner list-group-item-text\"> "+result2.getString("USER_ZIPCODE") 
					+"</p></div></div></div></div><div class=\"container\">";
			String sql = "select * from review where USER_ID="+id;
			
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next())
			{
				String sql3 = "select restaurant_name from restaurants where RESTAURANT_ID="+result.getString("restaurant_id");
				ResultSet result3 = DBQuery.getFromDB(sql3);
				result3.next();
				profiles+=" <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+result3.getString("restaurant_name")+"</div>"+
			     " <div class=\"panel-body\"><p > Date: "+result.getDate("REVIEW_DATE")
						+"</p><p>  Rating: "+result.getString("RATING")+"</p><p>"
			     +result.getString("REVIEW_DES")+"</p></div></div>";
			
				}
			profiles+="</div>";

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("profiles", profiles);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
