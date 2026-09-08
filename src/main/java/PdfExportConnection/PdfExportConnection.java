package PdfExportConnection;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PdfExportConnection {

    private static PdfExportConnection instance;

    private PdfExportConnection() {
    }

    public static PdfExportConnection getInstance() {
        if (instance == null) {
            instance = new PdfExportConnection();
        }
        return instance;
    }

    //  Get HTML String
    public void exportHTMLtoPDF(java.awt.Component parentView, String defaultFileName, String htmlContent) {
        
        int confirm = JOptionPane.showConfirmDialog(parentView, "Do you want to download the Professional PDF Report?", "Download PDF", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save PDF File");
            fileChooser.setSelectedFile(new File(defaultFileName));
            
            if (fileChooser.showSaveDialog(parentView) == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                
                try {
                    // Landscape
                    Document document = new Document(PageSize.A4.rotate());
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                    document.open();
                    
                    // Convert html code to PDF 
                    XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlContent));
                    
                    document.close();
                    
                    JOptionPane.showMessageDialog(parentView, "PDF Report downloaded successfully!\nSaved at: " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(parentView, "Error saving PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}