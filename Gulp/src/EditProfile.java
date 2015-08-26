

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private User user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("User");
		String header = "Edit Profile";
		request.setAttribute("headName",header);
		request.setAttribute("action","EditProfile");
		request.setAttribute("name",user.getName());
		request.setAttribute("email",user.getEmail());
		request.setAttribute("password",user.getPassword());
		request.setAttribute("zip",user.getZipcode());
		getServletContext().getRequestDispatcher("/SignUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name= user.getName();
			String email=user.getEmail();
			String pwd = user.getPassword();
			String zip = user.getZipcode();
			int id = user.getUser_id();
			DBQuery.openConnection();
			if(!request.getParameter("name").equals(""))
			name = request.getParameter("name");
			if(!request.getParameter("email").equals(""))
			email = request.getParameter("email");
			if(!request.getParameter("pwd").equals(""))
			pwd = request.getParameter("pwd");
			if(!request.getParameter("zip").equals(""))
			zip = request.getParameter("zip");
			String sql = "update userprofile set user_name='"+name+"',user_email='"+email+
					"',user_zipcode='"+zip+"',USER_PASS='"+pwd+"' where user_id="+id;
			DBQuery.updateDB(sql);
			user.setDetails(name, email, zip, pwd);
			HttpSession session = request.getSession();
			session.setAttribute("User", user);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("alertMessage", "<div class=\"container\"><div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"+
					    "<strong>Success!</strong> You have edited your Profile.</div></div>");
		doGet(request,response);
		//getServletContext().getRequestDispatcher("/SignUp.jsp").forward(request,
		//		response);
	}

	
}
