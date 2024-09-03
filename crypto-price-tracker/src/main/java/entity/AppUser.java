package entity;

import java.util.Date;
import java.util.Objects;

public class AppUser {
	private int id;
	private String nickname;
	private String email;
	private String password;
	private Role role;
	private Date created;
	private Date updated;
	
	public AppUser() {
	}

	public AppUser(int id, String nickname, String email, String password, Role role, Date created, Date updated) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.created = created;
		this.updated = updated;
	}

	public AppUser(String nickname, String email, String password, Role role, Date created) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", created=" + created + ", updated=" + updated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, email, id, nickname, password, role, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return Objects.equals(created, other.created) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(nickname, other.nickname) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(updated, other.updated);
	}
	

}
