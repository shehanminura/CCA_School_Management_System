package Model.entity;

public class UserEntity {
    
    private String userId;
    private String email;
    private String password;
    private String role;
    private String status;

    public UserEntity(String userId, String email, String password, String role, String status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
}