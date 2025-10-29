// User.java

public class User {
    private int userId;
    private String username;
    private String role;

    public User(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public boolean isAdmin() { return role.equals("ADMIN"); }
    public boolean isStudent() { return role.equals("STUDENT"); }
}
