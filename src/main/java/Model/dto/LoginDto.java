package Model.dto;

public class LoginDto {
    private String email;
    private String password;
    private String role;
    private String status;

   
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

  
    public LoginDto(String email, String role, String status) {
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
}