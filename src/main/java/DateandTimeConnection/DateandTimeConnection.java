/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DateandTimeConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateandTimeConnection {
    
    //  Static instance 
    private static DateandTimeConnection instance;
    
    //  Formatter
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;
    private final DateTimeFormatter dateTimeFormatter;

    // Private Constructor
    private DateandTimeConnection() {
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    // 4. Global Access Point (Singleton)
    public static DateandTimeConnection getInstance() {
        if (instance == null) {
            instance = new DateandTimeConnection();
        }
        return instance;
    }

    // Methods ටික

    // Return Current Date ( 2026-09-07 )
    public String getCurrentDate() {
        return LocalDate.now().format(dateFormatter);
    }

    // Return Current Time (20:45:12)
    public String getCurrentTime() {
        return LocalTime.now().format(timeFormatter);
    }

    // Return Current Date and Time (2026-09-07 20:45:12)
    public String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }
    
}