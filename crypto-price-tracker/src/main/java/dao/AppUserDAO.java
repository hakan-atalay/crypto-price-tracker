package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.AppUser;
import entity.Role;
import jakarta.faces.context.FacesContext;
import util.DBConnect;
import util.PasswordService;

public class AppUserDAO extends DBConnect {

	private final PasswordService passwordService;
	private  RoleDAO roleDAO;

	public PasswordService getPasswordService() {
		return passwordService;
	}

	public AppUserDAO() {
		this.passwordService = new PasswordService();
	}

	public List<AppUser> readList() {
		List<AppUser> userList = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = this.getConnect().prepareStatement("select * from app_users");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Role role = this.getRoleDAO().getById(resultSet.getInt("role_id"));
				userList.add(new AppUser(resultSet.getInt("id"), resultSet.getString("nickname"),
						resultSet.getString("email"), resultSet.getString("password"), role,
						resultSet.getTimestamp("created"), resultSet.getTimestamp("updated")));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return userList;
	}

	public void create(AppUser user) throws Exception {

		if (isNicknameExists(user.getNickname())) {
			throw new Exception("Zaten böyle bir takma ad mevcut");
		}

		try {
			String query = "insert into app_users(nickname, email, password, role_id, created) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getEmail());
			String hashedPassword = passwordService.hashPassword(user.getPassword());
			preparedStatement.setString(3, hashedPassword);
			preparedStatement.setInt(4, 2);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			preparedStatement.setTimestamp(5, timestamp);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean isNicknameExists(String nickname) {
		try {
			String query = "select count(*) from app_users where nickname=?";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setString(1, nickname);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void update(AppUser user) throws Exception {

		if (isNicknameExists(user.getNickname())) {
			throw new Exception("Zaten böyle bir takma ad mevcut");
		}

		try {
			String query = "update app_users set nickname=?, email=?, password=?, role_id=?, updated=? where id=?";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getEmail());
			String hashedPassword = passwordService.hashPassword(user.getPassword());
			preparedStatement.setString(3, hashedPassword);
			preparedStatement.setInt(4, user.getRole().getId());
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			preparedStatement.setTimestamp(5, timestamp);
			preparedStatement.setInt(6, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(AppUser user) {
		try {
			String query = "delete from app_users where id=?";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public AppUser getUser(String nickname, String password) {
		AppUser user = null;
		try {
			String query = "select * from app_users where nickname=?";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setString(1, nickname);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String storedHashedPassword = resultSet.getString("password");

				if (passwordService.verifyPassword(password, storedHashedPassword)) {
					user = new AppUser();
					user.setId(resultSet.getInt("id"));
					user.setNickname(resultSet.getString("nickname"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(storedHashedPassword);
					Role role = this.getRoleDAO().getById(resultSet.getInt("role_id"));
				    user.setRole(role);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;

	}
	

	public RoleDAO getRoleDAO() {
		if(roleDAO == null) {
			this.roleDAO = new RoleDAO();
		}
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
