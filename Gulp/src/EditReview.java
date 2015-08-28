

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
 * Servlet implementation class EditReview
 */
@WebServlet("/EditReview")
public class EditReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private model.Review review;
    private int restId;
    private model.Userprofile user;
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
		user = (model.Userprofile)request.getSession().getAttribute("User");
		review =reviewDetails(restId,user.getUserId());
		request.setAttribute("restaurants","<option>"+review.getRestaurant().getRestaurantName()+"</option>");
		request.setAttribute("description", review.getReviewDes());
		request.setAttribute("action", "EditReview");
		getServletContext().getRequestDispatcher("/Review.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		review.setRating(new BigDecimal(request.getParameter("optradio")));
		//System.out.println(date);
		review.setReviewDate(new Date());
		if(!request.getParameter("desc").equals(""))
			review.setReviewDes(request.getParameter("desc"));
	
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		String sql = "update Review r set r.reviewDate = :revDate, r.rating = :rating, r.reviewDes =:desc where"
				+ " r.userprofile.userId = :id and r.restaurant.restaurantName = :restName";
		
		TypedQuery<model.Review> reviewQuery = em.createQuery(sql,model.Review.class).setParameter("revDate", review.getReviewDate()).setParameter("rating",review.getRating())
		.setParameter("desc", review.getReviewDes()).setParameter("id", review.getUserprofile().getUserId()).setParameter("restName", review.getRestaurant().getRestaurantName());
		
		try{
			reviewQuery.executeUpdate();
			trans.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			trans.rollback();
		}
		finally{
			em.close();
		}
		getServletContext().getRequestDispatcher("/Profile").forward(request,
				response);
	}

	protected model.Review reviewDetails(int restId, long userId){
	//	String sql = "select review_des, restaurant_name from review join restaurants on review.restaurant_id = "+restId +
		//		" and review.user_id = " + userId +" and restaurants.restaurant_id="+restId;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
	
		String sql = "select r from Review r where r.restaurant.restaurantId = :restId and r.userprofile.userId = :userId";
		
		try {
			return (model.Review)em.createQuery(sql,model.Review.class).setParameter("restId", restId).setParameter("userId", userId).getSingleResult();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected void submitReviewEdit(Date date, String desc, int rating, int restId, long userId){
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
