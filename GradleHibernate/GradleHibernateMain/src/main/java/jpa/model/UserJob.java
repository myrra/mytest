package jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_jobs")
public class UserJob implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Id
	@ManyToOne(optional = true)
	@JoinColumn(name = "job_id")
	private Job job;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	


}
