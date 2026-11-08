package View;

import Controller.AIChatController;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChatPanel extends JPanel {

    private JPanel chatContainer;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JButton sendButton;
    private JPanel typingRow;
    private AIChatController controller;

    private final Color PRIMARY_BLUE = new Color(37, 99, 235);
    private final Color BG_LIGHT = new Color(241, 245, 249);
    private final Color TEXT_DARK = new Color(30, 41, 59);
    private final String PLACEHOLDER = "Ask EduMate AI anything about your studies...";

    public ChatPanel() {
        initComponents();
        this.controller = new AIChatController(this);
    }

    // 🔴 Dashboard එකේ උසට හරියටම ගැළපෙන ලෙස උස 380px දක්වා අඩු කර ඇත
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(660, 380);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(660, 380);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(2000, 390);
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        setBackground(BG_LIGHT);

        // ================= 1. TOP HEADER (COMPACT - 46px) =================
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(660, 46));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(226, 232, 240)),
                new EmptyBorder(5, 14, 5, 14)
        ));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftHeader.setOpaque(false);
        leftHeader.add(createAvatarBadge("AI", PRIMARY_BLUE, Color.WHITE, 30));

        JPanel titleGroup = new JPanel(new GridLayout(2, 1, 0, 0));
        titleGroup.setOpaque(false);

        JLabel titleLabel = new JLabel("EduMate AI Assistant");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(TEXT_DARK);

        JLabel statusLabel = new JLabel("Online  |  Smart Academic Helper");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        statusLabel.setForeground(new Color(22, 163, 74));

        titleGroup.add(titleLabel);
        titleGroup.add(statusLabel);
        leftHeader.add(titleGroup);

        JButton clearBtn = new JButton("Clear Chat") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? new Color(226, 232, 240) : new Color(241, 245, 249));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.setColor(new Color(203, 213, 225));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        clearBtn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        clearBtn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        clearBtn.setForeground(new Color(71, 85, 105));
        clearBtn.setContentAreaFilled(false);
        clearBtn.setFocusPainted(false);
        clearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearBtn.setBorder(new EmptyBorder(4, 10, 4, 10));
        clearBtn.addActionListener(e -> clearChat());

        headerPanel.add(leftHeader, BorderLayout.WEST);
        headerPanel.add(clearBtn, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // ================= 2. CENTER SCROLLABLE CHAT AREA (240px) =================
        chatContainer = new ScrollableChatPanel();
        chatContainer.setLayout(new BoxLayout(chatContainer, BoxLayout.Y_AXIS));
        chatContainer.setBackground(BG_LIGHT);
        chatContainer.setBorder(new EmptyBorder(8, 10, 8, 10));

        scrollPane = new JScrollPane(chatContainer);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(660, 240));
        add(scrollPane, BorderLayout.CENTER);

        // ================= 3. FIXED BOTTOM INPUT BAR (86px) =================
        JPanel southContainer = new JPanel(new BorderLayout(0, 5));
        southContainer.setBackground(Color.WHITE);
        southContainer.setPreferredSize(new Dimension(660, 86));
        southContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(226, 232, 240)),
                new EmptyBorder(6, 12, 10, 12)
        ));

        // Quick Suggestion Chips
        JPanel chipsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        chipsPanel.setOpaque(false);
        String[] suggestions = {"My Assignments", "My Subjects", "Google Drive Help", "Who are you?"};
        for (String text : suggestions) {
            chipsPanel.add(createChipButton(text));
        }
        southContainer.add(chipsPanel, BorderLayout.NORTH);

        // Text Field + Send Button Box
        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(248, 250, 252));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g2.setColor(new Color(203, 213, 225));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g2.dispose();
            }
        };
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(4, 12, 4, 5));

        inputField = new JTextField(PLACEHOLDER);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        inputField.setForeground(Color.GRAY);
        inputField.setBorder(null);
        inputField.setOpaque(false);

        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals(PLACEHOLDER)) {
                    inputField.setText("");
                    inputField.setForeground(TEXT_DARK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().trim().isEmpty()) {
                    inputField.setText(PLACEHOLDER);
                    inputField.setForeground(Color.GRAY);
                }
            }
        });

        sendButton = new JButton("Send") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? new Color(29, 78, 216) : PRIMARY_BLUE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        sendButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        sendButton.setFocusPainted(false);
        sendButton.setContentAreaFilled(false);
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendButton.setBorder(new EmptyBorder(6, 18, 6, 18));

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        southContainer.add(bottomPanel, BorderLayout.SOUTH);
        add(southContainer, BorderLayout.SOUTH);

        Runnable sendAction = () -> {
            String question = inputField.getText().trim();
            if (!question.isEmpty() && !question.equals(PLACEHOLDER)) {
                addMessage(question, true);
                inputField.setText("");
                showTypingIndicator(true);
                setInputEnabled(false);
                controller.processUserQuestion(question);
            }
        };

        sendButton.addActionListener(e -> sendAction.run());
        inputField.addActionListener(e -> sendAction.run());

        addMessage("Hello! I am EduMate AI. Select a quick topic below or type your question to get started.", false);
    }

    private static class ScrollableChatPanel extends JPanel implements Scrollable {
        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }
        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }
        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 64;
        }
        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }
        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }

    private JComponent createAvatarBadge(String label, Color bg, Color fg, int size) {
        JLabel badge = new JLabel(label, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setFont(new Font("Segoe UI", Font.BOLD, size == 30 ? 11 : 10));
        badge.setForeground(fg);
        badge.setPreferredSize(new Dimension(size, size));
        return badge;
    }

    private JButton createChipButton(String text) {
        JButton chip = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? new Color(219, 234, 254) : new Color(241, 245, 249));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.setColor(new Color(147, 197, 253));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        chip.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        chip.setFont(new Font("Segoe UI", Font.BOLD, 11));
        chip.setForeground(PRIMARY_BLUE);
        chip.setContentAreaFilled(false);
        chip.setFocusPainted(false);
        chip.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chip.setBorder(new EmptyBorder(3, 10, 3, 10));
        chip.addActionListener(e -> {
            addMessage(text, true);
            showTypingIndicator(true);
            setInputEnabled(false);
            controller.processUserQuestion(text);
        });
        return chip;
    }

    public void addMessage(String message, boolean isUser) {
        JPanel messageRow = new JPanel(new FlowLayout(isUser ? FlowLayout.RIGHT : FlowLayout.LEFT, 8, 3));
        messageRow.setOpaque(false);

        JPanel bubblePanel = new JPanel(new BorderLayout(0, 2)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isUser ? PRIMARY_BLUE : Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                if (!isUser) {
                    g2.setColor(new Color(226, 232, 240));
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                }
                g2.dispose();
            }
        };
        bubblePanel.setOpaque(false);
        bubblePanel.setBorder(new EmptyBorder(7, 12, 5, 12));

        // Format Markdown (**bold**) into clean HTML
        String formattedMsg = message.replace("&", "&amp;")
                                     .replace("<", "&lt;")
                                     .replace(">", "&gt;")
                                     .replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")
                                     .replace("\n", "<br>");

        String textColor = isUser ? "#FFFFFF" : "#1E293B";
        String widthStyle = message.length() > 50 ? "width: 340px;" : "";

        JLabel messageLabel = new JLabel(
                "<html><div style='font-family: Segoe UI; font-size: 10pt; color: " + textColor + "; " + widthStyle + "'>"
                + formattedMsg + "</div></html>"
        );

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        timeLabel.setForeground(isUser ? new Color(219, 234, 254) : new Color(148, 163, 184));
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        bubblePanel.add(messageLabel, BorderLayout.CENTER);
        bubblePanel.add(timeLabel, BorderLayout.SOUTH);

        if (isUser) {
            messageRow.add(bubblePanel);
            messageRow.add(createAvatarBadge("ME", new Color(30, 64, 175), Color.WHITE, 24));
        } else {
            messageRow.add(createAvatarBadge("AI", new Color(219, 234, 254), PRIMARY_BLUE, 24));
            messageRow.add(bubblePanel);
        }

        messageRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, messageRow.getPreferredSize().height + 6));

        chatContainer.add(messageRow);
        scrollToBottom();
    }

    public void showTypingIndicator(boolean show) {
        if (show) {
            if (typingRow == null) {
                typingRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 2));
                typingRow.setOpaque(false);
                JLabel typingLabel = new JLabel("EduMate AI is typing...");
                typingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
                typingLabel.setForeground(new Color(100, 116, 139));
                typingRow.add(typingLabel);
                typingRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
            }
            chatContainer.add(typingRow);
        } else if (typingRow != null) {
            chatContainer.remove(typingRow);
        }
        scrollToBottom();
    }

    public void appendAIResponse(String response) {
        showTypingIndicator(false);
        setInputEnabled(true);
        addMessage(response, false);
        inputField.requestFocusInWindow();
    }

    private void setInputEnabled(boolean enabled) {
        inputField.setEnabled(enabled);
        sendButton.setEnabled(enabled);
    }

    private void clearChat() {
        chatContainer.removeAll();
        addMessage("Chat history cleared! How can I assist you now?", false);
    }

    private void scrollToBottom() {
        chatContainer.revalidate();
        chatContainer.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
        });
    }
}