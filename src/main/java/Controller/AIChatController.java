package Controller;

import View.ChatPanel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.SwingWorker;

public class AIChatController {

    private final ChatPanel view;

    // 🔴 ඔබගේ Gemini API Key එක මෙතැනට දමන්න
    private static final String API_KEY = "apikey";

    public AIChatController(ChatPanel view) {
        this.view = view;
    }

    public void processUserQuestion(String question) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                // 1. Presentation එක සඳහා ක්ෂණික Offline පිළිතුරු
                String lowerQ = question.toLowerCase();
                
                if (lowerQ.contains("hi") || lowerQ.contains("hello")) {
                    Thread.sleep(400); // ස්වභාවික පෙනුමක් සඳහා කුඩා විරාමයක්
                    return "Hello! I am EduMate AI. How can I help with your studies today?";
                } else if (lowerQ.contains("assignment")) {
                    Thread.sleep(400);
                    return "You can view all your pending assignments in the 'My Assignment' section. Select a row and click 'Open Link' to open the Google Drive document.";
                } else if (lowerQ.contains("subject") || lowerQ.contains("module")) {
                    Thread.sleep(400);
                    return "Please check the 'My Subject' tab to see your enrolled subjects and subject codes.";
                } else if (lowerQ.contains("drive") || lowerQ.contains("link")) {
                    Thread.sleep(400);
                    return "Teachers upload assignments via Google Drive links. In the Student Panel, click any assignment row and press 'Open Link' to view it in your browser.";
                } else if (lowerQ.contains("name") || lowerQ.contains("who are you")) {
                    Thread.sleep(400);
                    return "I am EduMate AI Assistant, integrated into the School Management System to help students and teachers.";
                }

                // 2. අනෙකුත් ප්‍රශ්න සඳහා Gemini API කැඳවීම
                return callGemini(question);
            }

            @Override
            protected void done() {
                try {
                    String answer = get();
                    view.appendAIResponse(answer);
                } catch (Exception e) {
                    System.err.println("Worker Error: " + e.getMessage());
                    view.appendAIResponse("I am currently processing school records. Please use the side navigation tabs.");
                }
            }
        }.execute();
    }

    // URL එකක් වැරදීමකින් දැමුවද Key එක පමණක් වෙන් කරගැනීම
    private String getSanitizedKey() {
        String key = API_KEY.trim();
        if (key.contains("key=")) {
            key = key.substring(key.indexOf("key=") + 4);
            if (key.contains("&")) {
                key = key.substring(0, key.indexOf("&"));
            }
        }
        return key.trim();
    }

    private String callGemini(String prompt) {
        try {
            String cleanKey = getSanitizedKey();
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-lite-latest:generateContent?key=" + cleanKey;
            
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true);

            // Timeout සීමාව තත්පර 25 දක්වා වැඩි කර ඇත
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(25000);

            String escapedPrompt = prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
            String jsonInputString = "{\"contents\": [{\"parts\": [{\"text\": \"" + escapedPrompt + "\"}]}]}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        errorResponse.append(line.trim());
                    }
                    System.err.println("--- API Error Log ---");
                    System.err.println("Response Code: " + responseCode);
                    System.err.println("Error Details: " + errorResponse);
                    System.err.println("---------------------");
                }
                return "I'm currently in offline presentation mode! Ask me about 'Assignments', 'Subjects', or 'Google Drive Links' using the quick buttons below.";
            }

            try (Scanner scanner = new Scanner(conn.getInputStream(), "utf-8")) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                return extractGeminiText(response.toString());
            }

        } catch (SocketTimeoutException e) {
            System.err.println("Gemini API Timeout: " + e.getMessage());
            return "The network connection is slow right now. You can use the quick action buttons below for instant answers!";
        } catch (Exception e) {
            System.err.println("Connection Error: " + e.getMessage());
            return "My network is slightly busy right now! You can easily manage your tasks using the dashboard menu.";
        }
    }

    private String extractGeminiText(String json) {
        try {
            int textKey = json.indexOf("\"text\":");
            if (textKey == -1) return "I received your question. Please check the relevant dashboard section.";
            int startIndex = json.indexOf("\"", textKey + 7) + 1;

            StringBuilder result = new StringBuilder();
            boolean escaped = false;
            for (int i = startIndex; i < json.length(); i++) {
                char c = json.charAt(i);
                if (escaped) {
                    if (c == 'n') result.append('\n');
                    else if (c == '"') result.append('"');
                    else if (c == '\\') result.append('\\');
                    else result.append(c);
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    break;
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        } catch (Exception e) {
            return "Unable to parse response. Please try again.";
        }
    }
}