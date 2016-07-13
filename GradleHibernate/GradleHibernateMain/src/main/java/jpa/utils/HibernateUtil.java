package jpa.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import jpa.model.BaseModel;
import jpa.model.Job;
import jpa.model.User;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		Configuration configuration = new Configuration().configure("META-INF/hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		configuration.addAnnotatedClass(BaseModel.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Job.class);

		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {
//		try {
//			System.out.println(getSessionFactory());
//			Session session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			User u = new User();
//			u.setFirstName("mira");
//			u.setLastName("mir");
//			u.setAddress("address");
//			u.setBirthDate(new Date());
//			Job job = new Job();
//			job.setJobName("admin");
//			Set<Job> jobs = new HashSet<Job>();
//			jobs.add(job);
//			u.setJobs(jobs);
//			if (session != null && u != null)
//				session.saveOrUpdate(u);
//			session.getTransaction().commit();
//			session.close();
//			System.out.println("Done");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.exit(0);
	}
}
