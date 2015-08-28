

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class Restaraunts
 */
@WebServlet("/Restaurants")
public class Restaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restaurants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurants="";
		try {
			
			String sql = "select g from Restaurants g order by g.avgRating desc";
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			List<model.Restaurants> restList = em.createQuery(sql,model.Restaurants.class).getResultList();
			restaurants+=" <div class=\"container\"><div class=\"row\">";
			
			for(model.Restaurants r : restList){
				updateAverage(r.getRestaurantId());
				restaurants+="<div class=\"item  col-sm-6 col-sm-6\"> <div class=\"thumbnail\"> <div class=\"caption\">"+
		                  " <h4>"+
	                       r.getRestaurantName()+"</h4> <p>"+
	                       r.getRestaurantDes()+"</p> "+
	                       "<p> <b>Address: </b>"+
	                       r.getRestaurantAdd()+"</p>  <p><b>Rating: </b>"+
	                       r.getAvgRating()+"</p> <p> <b>Number of Rating: </b>"+
	                       r.getNumRating()+"</p> "+
	                       "<a class=\"btn btn-primary\" href=\"RestaurantReviews?Restaurantid="+r.getRestaurantId()+"\"> Reviews </a>"+
	                       "<a class=\"btn btn-primary\" href=\"EditRestaurant?Restaurantid="+r.getRestaurantId()+"\" &&> Edit </a></div></div></div>";          
			}

               
		   
			restaurants+="</div>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("restaurants", restaurants);
		getServletContext().getRequestDispatcher("/Restaurants.jsp").forward(request,
				response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void updateAverage(long id){
		
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		//	System.out.println(sql);
		try {
			String avgSql = "select avg(r.rating) as average from Review r "+
					"where r.restaurant.restaurantId = :id";
			TypedQuery<Double> avg = em.createQuery(avgSql, Double.class).setParameter("id", id);
			BigDecimal average = new BigDecimal(avg.getSingleResult()).setScale(2,RoundingMode.DOWN);
			System.out.println(average);
			String restSql = "update Restaurants g set g.avgRating = :avg where g.restaurantId= :id";
			
			TypedQuery<model.Restaurants> result = em.createQuery(restSql,model.Restaurants.class).setParameter("id", id)
					.setParameter("avg",average);
			result.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trans.rollback();
		}
		finally{
			em.close();
		}
	}

}
