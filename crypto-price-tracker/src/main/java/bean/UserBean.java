package bean;

import java.io.Serializable;
import java.util.List;

import dao.AppUserDAO;
import dao.RoleDAO;
import entity.AppUser;
import entity.Role;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {

	private AppUser user = new AppUser();
	private AppUserDAO userDAO = new AppUserDAO();
	private List<AppUser> userList;
	private List<Role> roles;

	public UserBean() {
	}
	
    @PostConstruct
    public void init() {
        RoleDAO roleDAO = new RoleDAO();
        this.roles = roleDAO.getAllRoles();
    }

	public String create() {
		try {
			this.userDAO.create(user);
			this.user = new AppUser();
			return "/user/login?faces-redirect=true";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Böyle bir takma ad zaten mevcut."));
			return "/user/register?faces-redirect=false";
		}

	}

	public String update() {
		try {
			this.userDAO.update(user);
			this.user = new AppUser();
			return "/admin/user-management?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Böyle bir takma ad zaten mevcut."));
			return "/admin/user-management?faces-redirect=false";
		}
	}

	public String updateForm(AppUser user) {
		this.user = user;
		return "/admin/user-update?faces-redirect=false";
	}

	public void delete(AppUser user) {
		this.userDAO.delete(user);
		this.user = new AppUser();
	}

	public AppUser getUser() {
		if (this.user == null) {
			this.user = new AppUser();
		}
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
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

	public List<AppUser> getUserList() {
		this.userList = this.getUserDAO().readList();
		return userList;
	}

	public void setUserList(List<AppUser> userList) {
		this.userList = userList;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles ) {
		this.roles = roles;
	}
	
}
