package jpa.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import jpa.exception.HibernateException;

public interface GenericDAO {

	public void setSessionFactory(SessionFactory sessionFactory);

	<T, ID> T findById(ID id) throws Exception;

	public <T> T save(final T o);

	public void delete(final Object object);

	public <T> T merge(final T o);

	public <T> T saveOrUpdate(final T o);

	public <T> List<T> findAll(final Class<T> type);

	public <T> List<T> findByCriteria(Criterion... criterion);

	public <T> T findByParameters(Map<String, String> params) throws HibernateException;
}
