package project1;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sport.shared.HibernateUtil;

@javax.annotation.ManagedBean
@SessionScoped
public class welcome {

	private String message = "ahmed mohamed mar";

	public welcome() {
		// TODO Auto-generated constructor stub

		HibernateUtil.getSessionFactory();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
