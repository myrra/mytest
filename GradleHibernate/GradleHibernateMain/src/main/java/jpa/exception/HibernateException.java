package jpa.exception;

public class HibernateException extends Exception {

	private static final long serialVersionUID = 7877480790028620950L;

	private boolean constrainsViolation = false;

	public HibernateException(String message) {
		super(message);
	}

	public HibernateException(Exception hibernateException) {
		super(hibernateException);
	}

	public boolean isConstrainsViolation() {
		return constrainsViolation;
	}

	public void setConstrainsViolation(boolean constrainsViolation) {
		this.constrainsViolation = constrainsViolation;
	}
}

