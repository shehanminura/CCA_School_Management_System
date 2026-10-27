package Model.dto;

public class AssignmentDto {
    private String assignmentId;
    private String subjectId;
    private String title;
    private String description;
    private String assignmentLink;
    private String dueDate;

    public AssignmentDto() {}

    public AssignmentDto(String assignmentId, String subjectId, String title, String description, String assignmentLink, String dueDate) {
        this.assignmentId = assignmentId;
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.assignmentLink = assignmentLink;
        this.dueDate = dueDate;
    }

    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssignmentLink() { return assignmentLink; }
    public void setAssignmentLink(String assignmentLink) { this.assignmentLink = assignmentLink; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
}
