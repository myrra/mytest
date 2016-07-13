package jpa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jpa.exception.HibernateException;
import jpa.model.User;

public abstract class GenericHibernateDAO implements GenericDAO {

	private Class<Object> persistentClass;

	private Session session;
	@Autowired(required = true)
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("Sessionfactory value = " + this.sessionFactory);
	}

	protected Session getSession() {
		if (session == null) {
			session = this.sessionFactory.openSession();
		} else {
			session = this.sessionFactory.getCurrentSession();
		}

		return session;
	}

	public Class<Object> getPersistentClass() {
		return persistentClass;
	}

	@Override
	@Transactional
	@SuppressWarnings({ "unchecked" })
	public <T, ID> T findById(ID id) {
		session = getSession();
		if (session != null) {
			System.out.println("id = " + id);
			T o = (T) session.load(User.class, (Serializable) id);
			return o;
		} else {
			System.out.println("session null");
			return null;
		}
	}

	@Override
	@Transactional
	@SuppressWarnings({ "unchecked" })
	public <T> T findByParameters(Map<String, String> params) throws HibernateException {
		session = getSession();
		if (session != null) {
			Criterion[] cList = new Criterion[params.size()];
			int index = 0;
			for (Map.Entry<String, String> entry : params.entrySet())
			{
				Criterion c = Restrictions.eq(entry.getKey(), entry.getValue());
				cList[index] = c;
				index++;
			}

			T user = (T) findByCriteria(cList).get(0);
			return user;
		} else {
			System.out.println("session null");
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> T save(final T o) {
		return (T) sessionFactory.getCurrentSession().save(o);
	}

	@Override
	public void delete(final Object object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> T merge(final T o) {
		return (T) sessionFactory.getCurrentSession().merge(o);
	}

	@Override
	@Transactional
	public <T> T saveOrUpdate(final T o) {
		sessionFactory.getCurrentSession().saveOrUpdate(o);
		return o;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> findAll(final Class<T> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);
		return crit.list();
	}

}
