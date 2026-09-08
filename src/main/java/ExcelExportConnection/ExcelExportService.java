package ExcelExportConnection; // ඔයාගේ පැකේජ් නමට ගැලපෙන පරිදි වෙනස් කරගන්න

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ExcelExportService {

    public static void exportToCSV(java.awt.Component parentView, String defaultFileName, String headers, List<String> dataRows) {
        
        int confirm = JOptionPane.showConfirmDialog(parentView, 
                "Do you want to download the data as an Excel file?", 
                "Download Report", 
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            fileChooser.setSelectedFile(new File(defaultFileName));
            
            int userSelection = fileChooser.showSaveDialog(parentView);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                
                try (FileWriter fw = new FileWriter(fileToSave);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                     
                    //Write List Headers
                    bw.write(headers + "\n");
                    
                    // Write List Data List 
                    for(String row : dataRows) {
                        bw.write(row + "\n");
                    }
                    
                    JOptionPane.showMessageDialog(parentView, "Excel file downloaded successfully!\nSaved at: " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(parentView, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}