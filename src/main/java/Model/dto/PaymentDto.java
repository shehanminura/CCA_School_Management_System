package Model.dto;

public class PaymentDto {
    private String statusId;
    private String studentId;
    private String studentName;
    private String classId;
    private String className;
    private String feeStatus;
    private String remark;
    private String updatedDate;

    public PaymentDto() {}

    public PaymentDto(String statusId, String studentId, String studentName, String classId, String className, String feeStatus, String remark, String updatedDate) {
        this.statusId = statusId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.classId = classId;
        this.className = className;
        this.feeStatus = feeStatus;
        this.remark = remark;
        // only get date remove any time format
        this.updatedDate = (updatedDate != null && updatedDate.length() > 10) ? updatedDate.substring(0, 10) : updatedDate;
    }

    public String getStatusId() { return statusId; }
    public void setStatusId(String statusId) { this.statusId = statusId; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    
    public String getFeeStatus() { return feeStatus; }
    public void setFeeStatus(String feeStatus) { this.feeStatus = feeStatus; }
    
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    
    public String getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(String updatedDate) { 
        this.updatedDate = (updatedDate != null && updatedDate.length() > 10) ? updatedDate.substring(0, 10) : updatedDate;
    }
}