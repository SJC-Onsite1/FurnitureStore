package com.exposit.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.exposit.domain.model.Feedback;
import com.exposit.domain.model.ProductCatalogUnit;
import com.exposit.domain.model.User;
import com.exposit.repository.dao.FeedbackDao;
import com.exposit.repository.hibernate.AbstractHibernateDao;

@Repository()
public class FeedbackRepository extends AbstractHibernateDao<Feedback, Integer>
		implements FeedbackDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getFeedacksList(User user) {
		Criteria criteria = this.getSession()
				.createCriteria(Feedback.class, "feedback")
				.add(Restrictions.eq("user", user))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Feedback>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getFeedbackList(ProductCatalogUnit catalogUnit) {
		Criteria criteria = this.getSession().createCriteria(Feedback.class, "feedback")
				.add(Restrictions.eq("productCatalogUnit", catalogUnit));
		return (List<Feedback>) criteria.list();
	}

	@Override
	public double getAverageMark(ProductCatalogUnit catalogUnit) {
		Criteria criteria = this.getSession().createCriteria(Feedback.class, "feedback")
		.add(Restrictions.eq("productCatalogUnit", catalogUnit))
		.setProjection(Projections.avg("range"));
		return (double)((Double) criteria.uniqueResult());
	}
	
}