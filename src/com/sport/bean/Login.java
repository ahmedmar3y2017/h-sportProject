package com.sport.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "login", eager = true)
@SessionScoped
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@PostConstruct
	public void inititlize() {

	}

	public void grantPermission() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Object adminLoggedIn = session.getAttribute("admin");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if (adminLoggedIn == null) {
			try {
				context.redirect("login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String logout() {
		HttpSession session = SessionUtil.getSession();
		session.invalidate();
		return "index.xhtml?faces-redirected=true";
	}

	public String login() {

		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {

			HttpSession session = SessionUtil.getSession();
			session.setAttribute("admin", "admin");

			return "dashboard?faces-redirect=true";

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Useraname or password", null));
			return "login";
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
