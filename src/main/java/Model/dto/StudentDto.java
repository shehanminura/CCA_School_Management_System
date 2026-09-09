package Model.dto;

public class StudentDto {
    private String studentId;
    private String name;
    private String birthday;
    private String contactNumber;
    private String email;
    private String address;
    private String gender;
    private String classId; // අලුතින් එකතු කළ Class ID එක
    private String password;
    private String status;
    private String className;


    public StudentDto() {}

    // Constructor එකට classId එකත් එකතු කර ඇත
    public StudentDto(String studentId, String name, String birthday, String contactNumber, String email, String address, String gender, String classId, String password, String status) {
        this.studentId = studentId;
        this.name = name;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.classId = classId;
        this.password = password;
        this.status = status;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
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
    
    // classId සඳහා Getter සහ Setter
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}