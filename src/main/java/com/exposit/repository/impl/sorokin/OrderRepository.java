package com.exposit.repository.impl.sorokin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exposit.domain.model.sorokin.Order;
import com.exposit.domain.model.sorokin.User;
import com.exposit.repository.dao.sorokin.OrderDao;
import com.exposit.repository.hibernate.AbstractHibernateDao;

@Repository()
public class OrderRepository extends AbstractHibernateDao<Order, Integer>
		implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getListOfUserOrders(User user) {
		Criteria cr = getSession().createCriteria(Order.class).add(
				Restrictions.eq("user", user));
		return (List<Order>) cr.list();
	}
}
