

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
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		getServletContext().getRequestDispatcher("/SignIn.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBQuery.openConnection();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String sql = "select * from userprofile where user_email = '"+email+
				"' and user_pass = '"+pwd+"'";
		//System.out.println(sql);
		HttpSession session = request.getSession(true);
		try {
			ResultSet logIn = DBQuery.getFromDB(sql);
			if(logIn.next()){
				User user = new User(logIn.getInt("user_id"));
				user.setDetails(logIn.getString("user_name"), logIn.getString("user_email"), logIn.getString("user_zipcode"), logIn.getString("user_pass"));
				//user.setLoggedIn(true);
				session.setAttribute("loggedIn", true);
				session.setAttribute("User",user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String logIn="<div class=\"container\"><div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"+
    "<strong>Success!</strong> You have logged in.</div></div>";
    	request.setAttribute("logIn", logIn);
		getServletContext().getRequestDispatcher("/index.jsp?logOut=false").forward(request,
				response);
	}

}
