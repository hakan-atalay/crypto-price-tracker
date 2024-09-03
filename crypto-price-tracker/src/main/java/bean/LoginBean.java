package bean;

import java.io.Serializable;

import dao.AppUserDAO;
import entity.AppUser;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private String nickname;
	private String password;

	private AppUserDAO userDAO;

	public LoginBean() {
	}

	public String login() {
		AppUser user = this.getUserDAO().getUser(this.nickname, this.password);
		if (user != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
			return "/dashboard/index?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("takma ad veya şifre yanlış."));
			return "/user/login?faces-redirect=false";
		}

	}

	public AppUser getLoggedInUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (AppUser) facesContext.getExternalContext().getSessionMap().get("user");
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUserDAO getUserDAO() {
		if (this.userDAO == null) {
			this.userDAO = new AppUserDAO();
		}
		return userDAO;
	}

	public void setUserDAO(AppUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
