package Model.entity;

public class TeacherEntity {
    private String teacherId;
    private String name;
    private String nic;
    private String birthday;
    private String contactNumber;
    private String email;
    private String address;
    private String gender;
    private double salary; // Handled as double for Database
    private String password;
    private String status;
    private String registrationDate;

    public TeacherEntity() {}

    public TeacherEntity(String teacherId, String name, String nic, String birthday, String contactNumber, String email, String address, String gender, double salary, String password, String status) {
        this.teacherId = teacherId;
        this.name = name;
        this.nic = nic;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.salary = salary;
        this.password = password;
        this.status = status;
    }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }
}