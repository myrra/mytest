package service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jpa.dao.GenericDAO;
import jpa.model.User;

@Component(value = "userService")
public class UserService {

	static Logger log = Logger.getLogger("UserService");

	@Autowired
	private User user;

	@Autowired
	@Qualifier("userDao")
	private GenericDAO userDao;

	public User findUser(String username, String password) {

		try {
			Map<String, String> params = new HashMap<>();
			params.put("username", username);
			params.put("password", password);
			user = (User) userDao.findByParameters(params);
			log.info(user.getFirstName() + " " + user.getLastName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public void addUser() {
		user.setFirstName("Fotache");
		user.setLastName("Mirela");
		user.setAddress("Brasov, jud. Bv");
		user.setBirthDate(new Date());
		user.setUsername("admin");
		user.setPassword("admin");
		userDao.saveOrUpdate(user);
	}

}
