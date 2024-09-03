package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordService {

	public String hashPassword(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
	}
	
	public boolean verifyPassword(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}
