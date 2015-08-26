import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditRestaurant
 */
@WebServlet("/EditRestaurant")
public class EditRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String edit = "";
	private Integer idint = 0;
	private String name = "";
	private String address = "";
	private String des = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditRestaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		edit = "";
		String id = request.getParameter("Restaurantid");
		name = request.getParameter("Restaurantname");
		address = request.getParameter("Restaurantadd");
		des = request.getParameter("Restaurantde");

		edit += "<div class=\"container\">";

		edit += "<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Name:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" placeholder=\""
				+ name
				+ "\"></div></div>"
				+

				"<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Address:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"address\" name=\"address\" placeholder=\""
				+ address
				+ "\"></div></div>"
				+

				"<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Description:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"description\" name=\"description\" placeholder=\""
				+ des
				+ "\"></div></div>"
				+ "<div class=\"form-group\"><div class=\"col-sm-offset-2 col-sm-10\"><button type=\"submit\" class=\"btn btn-default\">Submit</button></div>	</div>";

		edit += "</div>";

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("edit", edit);
		getServletContext().getRequestDispatcher("/EditRestaurant.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		edit = "";
		try {
			DBQuery.openConnection();
			if (request.getParameter("name") != name
					&& request.getParameter("name") != "")
				name = request.getParameter("name");
			if (request.getParameter("address") != address
					&& request.getParameter("address") != "")
				address = request.getParameter("address");
			if (request.getParameter("description") != des
					&& request.getParameter("des") != "")
				des = request.getParameter("description");
			String sql = "update restaurants set RESTAURANT_NAME='" + name
					+ "',RESTAURANT_ADD='" + address + "',RESTAURANT_DES='"
					+ des + "' where RESTAURANT_ID=" + idint;
			System.out.println(sql);
			DBQuery.updateDB(sql);
			edit = "<div class=\"alert alert-success\"> <strong>Success!</strong> restaurant was updated.</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("edit", edit);
		getServletContext().getRequestDispatcher("/EditRestaurant.jsp")
				.forward(request, response);
	}

}
