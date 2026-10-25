package Model.entity;

public class PaymentEntity {
    private String statusId;
    private String studentId;
    private String classId;
    private String feeStatus;
    private String remark;
    private String updatedDate;

    public PaymentEntity() {}

    public PaymentEntity(String statusId, String studentId, String classId, String feeStatus, String remark, String updatedDate) {
        this.statusId = statusId;
        this.studentId = studentId;
        this.classId = classId;
        this.feeStatus = feeStatus;
        this.remark = remark;
        this.updatedDate = updatedDate;
    }

    public String getStatusId() { return statusId; }
    public void setStatusId(String statusId) { this.statusId = statusId; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    
    public String getFeeStatus() { return feeStatus; }
    public void setFeeStatus(String feeStatus) { this.feeStatus = feeStatus; }
    
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    
    public String getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(String updatedDate) { this.updatedDate = updatedDate; }
}