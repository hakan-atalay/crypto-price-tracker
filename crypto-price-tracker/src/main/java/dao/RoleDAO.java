package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Role;
import util.DBConnect;

public class RoleDAO extends DBConnect {

	public RoleDAO() {
	}

	public Role getById(int id) {
		Role role = null;
		try {
			String query = "select * from roles where id=?";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setRoleName(resultSet.getString("role_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return role;
	}

	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		try {
			String query = "SELECT * FROM roles";
			PreparedStatement preparedStatement = this.getConnect().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setRoleName(resultSet.getString("role_name"));
				roles.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return roles;
	}
}
