package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.FeedbackDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Feedback;
@Repository("feedbackService")
public class FeedbackServiceImpl implements FeedbackServices{
	@Autowired
    private FeedbackDAO feedbackDAO;

	@Override
	public Integer add(Feedback model, Context ctx) throws Exception {
		Integer i= feedbackDAO.add(model, ctx);
		
		return i;
	}

	@Override
	public List<Feedback> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> findAll(Context ctx) {
		return feedbackDAO.findAll(ctx);
	}

	@Override
	public Feedback findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Feedback model, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
