package service;
import java.sql.SQLException;

import dao.ReviewDao;
public class ReviewService {
	private ReviewDao reviewDao_obj=new ReviewDao();;
	public void addToReview(int b_id, int p_id, double rating, String review) throws SQLException {
		reviewDao_obj.addToReview(b_id,p_id,rating,review);
	}
	
}
