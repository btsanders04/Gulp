

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Restaraunts
 */
@WebServlet("/Restaurants")
public class Restaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private String restaurants="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restaurants() {
       	super();
    	System.out.println("init");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		restaurants ="XXX";
		
		System.out.println("Get");
		// Actual logic goes here.
		request.setAttribute("restaurants", restaurants);
		getServletContext().getRequestDispatcher("/Restaurants.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		
	}

}
