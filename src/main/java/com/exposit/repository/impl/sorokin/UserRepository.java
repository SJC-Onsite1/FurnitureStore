package com.exposit.repository.impl.sorokin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.exposit.domain.model.sorokin.Bonus;
import com.exposit.domain.model.sorokin.Role;
import com.exposit.domain.model.sorokin.User;
import com.exposit.repository.dao.sorokin.UserDao;
import com.exposit.repository.hibernate.AbstractHibernateDao;

@Repository()
public class UserRepository extends AbstractHibernateDao<User, Integer>
		implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListOfAllUsersByRole(Role role) {
		Criteria cr = getSession().createCriteria(User.class, "user").add(
				Restrictions.eq("role", role));
		return (List<User>) cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListOfAllUsersByBonus(Bonus bonus) {
		Criteria cr = getSession().createCriteria(User.class, "user").add(
				Restrictions.eq("bonus", bonus));
		return (List<User>) cr.list();
	}

	@Override
	public User findUserByLoginAndPassword(String login, String password) {
		Criteria cr = getSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("password", password));
		return (User) cr.uniqueResult();
	}

	@Override
	public User findUserByName(String username) {
		Criteria cr = getSession().createCriteria(User.class).add(
				Restrictions.eq("login", username));
		return (User) cr.uniqueResult();
	}

}
