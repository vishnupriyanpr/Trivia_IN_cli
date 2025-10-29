// AuthService.java

import java.sql.*;

public class AuthService {

    public static User login(String username, String password) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT user_id, username, password_hash, role FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (PasswordHasher.verify(password, storedHash)) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("role")
                    );
                }
            }
            return null;

        } catch (SQLException e) {
            UIHelper.printError("Database error: " + e.getMessage());
            return null;
        }
    }

    public static boolean register(String username, String password, String role) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, PasswordHasher.hash(password));
            stmt.setString(3, role);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            UIHelper.printError("Registration failed: " + e.getMessage());
            return false;
        }
    }
}
