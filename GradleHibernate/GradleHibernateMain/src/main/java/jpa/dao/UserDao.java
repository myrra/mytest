package jpa.dao;

import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jpa.exception.HibernateException;
import jpa.model.User;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component(value = "userDao")
@Transactional
public class UserDao extends GenericHibernateDAO {

	static Logger log = Logger.getLogger("UserDao");

	@Autowired
	private User user;

	/**
	 * Get User from database by ID
	 * 
	 * @param ID
	 * @return User entity
	 */
	public User getUserById(int idUser) throws HibernateException {
		SessionFactory sf = this.sessionFactory;
		Session session = sf.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("id", idUser));
			user = (User) cr.uniqueResult();
		} catch (javax.validation.ConstraintViolationException e) {
			HibernateException he = new HibernateException(e);
			he.setConstrainsViolation(true);
			throw he;
		}

		return user;
	}


	public <T> T findByParameters(Map<String, String> params) throws HibernateException {
		SessionFactory sf = this.sessionFactory;
		Session session = sf.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(User.class);
			for (Map.Entry<String, String> entry : params.entrySet()) {

				log.info(">>>>>>>>>>>>>>>>>> key = " + entry.getKey());
				log.info(">>>>>>>>>>>>>>>>>> value = " + entry.getValue());
				cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}

			user = (User) cr.uniqueResult();
		} catch (javax.validation.ConstraintViolationException e) {
			HibernateException he = new HibernateException(e);
			he.setConstrainsViolation(true);
			throw he;
		}

		return (T) user;
	}

}
