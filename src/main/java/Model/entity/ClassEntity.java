package Model.entity;

public class ClassEntity {
    private String classId;
    private String className;

    public ClassEntity(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public String getClassId() { return classId; }
    public String getClassName() { return className; }

    // JComboBox එකේ දිස්විය යුතු නම (පරිශීලකයාට පෙනෙන නම) සැකසීම
    @Override
    public String toString() {
        return className; 
    }
}