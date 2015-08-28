

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private model.Userprofile user;
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
		user = (model.Userprofile) session.getAttribute("User");
		String header = "Edit Profile";
		request.setAttribute("headName",header);
		request.setAttribute("action","EditProfile");
		request.setAttribute("name",user.getUserName());
		request.setAttribute("email",user.getUserEmail());
		request.setAttribute("password",user.getUserPass());
		request.setAttribute("zip",user.getUserZipcode());
		getServletContext().getRequestDispatcher("/SignUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			
			long id = user.getUserId();
			if(!request.getParameter("name").equals(""))
			user.setUserName(request.getParameter("name"));
			if(!request.getParameter("email").equals(""))
			user.setUserEmail(request.getParameter("email"));
			if(!request.getParameter("pwd").equals(""))
			user.setUserPass(request.getParameter("pwd"));
			if(!request.getParameter("zip").equals(""))
			user.setUserZipcode(request.getParameter("zip"));
			
			String sql = "update Userprofile u set u.userName= :name , u.userEmail= :email" +
					",u.userZipcode= :zip ,u.userPass = :pass where u.userId= :id";
			em.createQuery(sql,model.Userprofile.class).setParameter("name", user.getUserName()).setParameter("email",user.getUserEmail())
			.setParameter("zip", user.getUserZipcode()).setParameter("pass",user.getUserPass()).setParameter("id", id);
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
