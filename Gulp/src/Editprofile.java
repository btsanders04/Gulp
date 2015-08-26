

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Editprofile
 */
@WebServlet("/Editprofile")
public class Editprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String edit="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editprofile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid");
		request.setAttribute("action","Editprofile");
		edit="";
		DBQuery.openConnection();
		try
		{
			String sql = "select * from userprofile where user_id="+Integer.parseInt(id);
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next())
			{
			/*	edit+="	<div class="form-group">
				<label class="control-label col-sm-2" for="">Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="Enter name">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="Enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pwd" name="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Zip Code:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="zip" name="zip"
						placeholder="Enter zip code">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">"
				request.getP.setAttribute("name", result.getString("USER_NAME"));
				request.setAttribute("email", result.getString("USER_EMAIL"));
				request.setAttribute("pwd", result.getString("USER_PASS"));
				request.setAttribute("zip", result.getString("USER_EMAIL"));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		getServletContext().getRequestDispatcher("/Editprofile.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
