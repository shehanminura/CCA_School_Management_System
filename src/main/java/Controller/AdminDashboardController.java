package Controller;

import Model.dao.AdminDashboardDAO;
import java.util.Map;
import org.jfree.data.general.DefaultPieDataset;

public class AdminDashboardController extends AdminPaymentController {
    
    private final AdminDashboardDAO dao;

    public AdminDashboardController() {
        super(null); 
        this.dao = new AdminDashboardDAO();
    }

    // 1. Payment Status Pie Dataset
    public DefaultPieDataset createPaymentPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> counts = dao.getPaymentStatusCounts();
        
        dataset.setValue("Paid", counts.getOrDefault("Paid", 0));
        dataset.setValue("Not Paid", counts.getOrDefault("Not Paid", 0));
        dataset.setValue("Exempted", counts.getOrDefault("Exempted", 0));
        
        return dataset;
    }

    // 2. System Users Pie Dataset (Student, Teacher, Admin)
    public DefaultPieDataset createUserPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> counts = dao.getUserRoleCounts();
        
        dataset.setValue("Student", counts.getOrDefault("Student", 0));
        dataset.setValue("Teacher", counts.getOrDefault("Teacher", 0));
        dataset.setValue("Admin", counts.getOrDefault("Admin", 0));
        
        return dataset;
    }
    
    public void downloadPaymentReportPDF(java.awt.Component parentComponent) {
        try {
            // extends කර ඇති නිසා this.exportToPDF() ලෙස කෙලින්ම පාවිච්චි කළ හැක
            super.exportToPDF();
            
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(parentComponent, 
                "Error generating PDF: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
