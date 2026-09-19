package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class SupportPopupView {

    public static String[] showSupportTicketDialog(Component parentView) {
        
        // ප්‍රධාන පැනලය (Compact Height)
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(440, 280));

        // 1. HEADER PANEL
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(122, 28, 44)); // Maroon (#7A1C2C)
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("Central College Anuradhapura");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubTitle = new JLabel("SYSTEM SUPPORT TICKET");
        lblSubTitle.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblSubTitle.setForeground(new Color(212, 175, 55)); // Gold (#D4AF37)
        lblSubTitle.setBorder(new EmptyBorder(2, 0, 0, 0));
        lblSubTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(lblTitle);
        headerPanel.add(lblSubTitle);

        JPanel goldLine = new JPanel();
        goldLine.setBackground(new Color(212, 175, 55));
        goldLine.setPreferredSize(new Dimension(0, 2));

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(headerPanel, BorderLayout.CENTER);
        topContainer.add(goldLine, BorderLayout.SOUTH);

        mainPanel.add(topContainer, BorderLayout.NORTH);

        // 2. FORM CONTENT PANEL
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(8, 15, 8, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // User ID Label
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 3, 0);
        JLabel lblUser = new JLabel("Your System ID or Email:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUser.setForeground(new Color(70, 70, 70));
        formPanel.add(lblUser, gbc);

        // User ID TextField
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        JTextField txtUserInfo = new JTextField();
        txtUserInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtUserInfo.setPreferredSize(new Dimension(0, 28));
        txtUserInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        formPanel.add(txtUserInfo, gbc);

        // Message Label
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 3, 0);
        JLabel lblMsg = new JLabel("Describe Your Request / Issue:");
        lblMsg.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMsg.setForeground(new Color(70, 70, 70));
        formPanel.add(lblMsg, gbc);

        // Message JTextArea with ScrollPane (අවශ්‍ය ප්‍රමාණයට සැකසූ neat design එකක්)
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        JTextArea txtMessage = new JTextArea();
        txtMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtMessage.setLineWrap(true);
        txtMessage.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(txtMessage);
        scrollPane.setPreferredSize(new Dimension(0, 50));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        formPanel.add(scrollPane, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 3. FILE ATTACHMENT PANEL
        JPanel filePanel = new JPanel(new BorderLayout(10, 0));
        filePanel.setBackground(new Color(245, 247, 250));
        filePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 224, 230)),
            new EmptyBorder(8, 15, 8, 15)
        ));

        JButton btnBrowse = new JButton("📎 Attach File");
        btnBrowse.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnBrowse.setBackground(new Color(235, 238, 242));
        btnBrowse.setForeground(new Color(60, 60, 60));
        btnBrowse.setFocusPainted(false);
        btnBrowse.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(205, 210, 218)),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        btnBrowse.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBrowse.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnBrowse.setBackground(new Color(222, 226, 232));
            }
            public void mouseExited(MouseEvent evt) {
                btnBrowse.setBackground(new Color(235, 238, 242));
            }
        });

        JLabel lblFileName = new JLabel("No file selected");
        lblFileName.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblFileName.setForeground(new Color(130, 130, 130));

        final String[] selectedFilePath = new String[1];

        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(parentWindow(parentView));
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFilePath[0] = selectedFile.getAbsolutePath();
                    lblFileName.setText(selectedFile.getName());
                    lblFileName.setForeground(new Color(0, 128, 64)); 
                    lblFileName.setFont(new Font("Segoe UI", Font.BOLD, 11));
                }
            }
        });

        filePanel.add(btnBrowse, BorderLayout.WEST);
        filePanel.add(lblFileName, BorderLayout.CENTER);

        mainPanel.add(filePanel, BorderLayout.SOUTH);

        int option = JOptionPane.showConfirmDialog(
            parentView, 
            mainPanel, 
            "CCA Management System - Support Center", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            String userInfo = txtUserInfo.getText().trim();
            String issue = txtMessage.getText().trim();
            
            if (userInfo.isEmpty() || issue.isEmpty()) {
                JOptionPane.showMessageDialog(parentView, "Please fill in all required fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                return null; 
            }
            
            return new String[]{
                userInfo,
                issue,
                selectedFilePath[0] 
            };
        }
        
        return null; 
    }

    private static Window parentWindow(Component c) {
        return c instanceof Window ? (Window) c : SwingUtilities.getWindowAncestor(c);
    }
}