import java.io.IOException;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class EditRestaurant
 */
@WebServlet("/EditRestaurant")
public class EditRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String edit="";
	private model.Restaurants restaurant;
	private long id;
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
		Long id = Long.parseLong(request.getParameter("Restaurantid"));
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		restaurant = em.createQuery("select g from Restaurants g where g.restaurantId = :id",model.Restaurants.class).setParameter("id", id).getSingleResult();
		edit += "<div class=\"container\">";

		edit += "<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Name:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" placeholder=\""
				+ restaurant.getRestaurantName()
				+ "\"></div></div>"
				+

				"<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Address:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"address\" name=\"address\" placeholder=\""
				+ restaurant.getRestaurantAdd()
				+ "\"></div></div>"
				+

				"<div class=\"form-group\"><label class=\"control-label col-sm-2\" for=\"\">Restaurant Description:</label><div class=\"col-sm-10\">"
				+ "<input type=\"text\" class=\"form-control\" id=\"description\" name=\"description\" placeholder=\""
				+ restaurant.getRestaurantDes()
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
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			
			
			if (!request.getParameter("name").equals(""))
				restaurant.setRestaurantName(request.getParameter("name"));
			if (!request.getParameter("address").equals(""))
				restaurant.setRestaurantAdd(request.getParameter("address"));
			if (!request.getParameter("description").equals(""))
				restaurant.setRestaurantDes(request.getParameter("description"));
			String sql = "update Restaurants r set r.restaurantName = :name"+
				 ", r.restaurantAdd = :restAdd , r.restaurantDes = :restDes"
					+ " where r.restaurantId = :id";
			em.createQuery(sql,model.Restaurants.class).setParameter("name", restaurant.getRestaurantName()).setParameter("restAdd",restaurant.getRestaurantAdd())
			.setParameter("restDes",restaurant.getRestaurantDes()).setParameter("id", restaurant.getRestaurantId()).executeUpdate();
			trans.commit();
			edit = "<div class=\"alert alert-success\"> <strong>Success!</strong> restaurant was updated.</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trans.rollback();
		}

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("edit", edit);
		getServletContext().getRequestDispatcher("/EditRestaurant.jsp")
				.forward(request, response);
	}

}
