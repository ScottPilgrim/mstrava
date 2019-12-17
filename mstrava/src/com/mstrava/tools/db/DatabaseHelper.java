package com.mstrava.tools.db;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
    private static String url = "jdbc:mysql://localhost:3306/mstrava?useSSL=false";
    private static String user = "mstrava";
    private static String password = "mstrava";
    private static String insertRunQuery = "INSERT INTO runs VALUES (default, (SELECT user_id FROM users WHERE login=(?)), (?), (?), (?), (?));";
    private static String selectAverage = "SELECT AVG(distance), AVG(calories) FROM runs WHERE user_id=(SELECT user_id FROM users WHERE login=(?)) AND runs.start >= (?) AND runs.end <= (?);";

    public static void sendRunUpdate(String username, int distance, int calories, Timestamp start, Timestamp end) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(insertRunQuery)) {
            pst.setString(1, username);
            pst.setString(2, Integer.toString(distance));
            pst.setString(3, Integer.toString(calories));
            pst.setString(4, start.toString());
            pst.setString(5, end.toString());
            pst.executeUpdate();
        }
    }

    public static String sendGetStats(String username, Timestamp start, Timestamp end) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(selectAverage)) {
            pst.setString(1, username);
            pst.setString(2, start.toString());
            pst.setString(3, end.toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String jsonOutput = "{\"average_ran_meters\":" + rs.getString(1) + ",\"average_burnt_calories\":" + rs.getString(2) + "}";
                return jsonOutput;
            }
        }
        throw new SQLException("No valid answer from database");
    }

    @Test
    void test1() throws SQLException {
        Timestamp start = Timestamp.valueOf("2019-12-31 15:00:00");
        Timestamp end = new Timestamp(System.currentTimeMillis());
        DatabaseHelper.sendRunUpdate("ivan", 8431, 530, start, end);
    }
}


