package view;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jpa.model.User;
import service.UserService;

@Component(value = "userView")
public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private User user;

	@Autowired
	public UserService userService;

	public UserView() {
		super();
	}

	@PostConstruct
	public void init() {
		if (userService != null) {
			userService.addUser();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
