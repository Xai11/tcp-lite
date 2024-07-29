package org.example.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Statement statement;
    private ResultSet resultSet;

    private Connection connectionDatabase() {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + "cardexinfo",
                    "postgres", "root");
            if(conn != null){

            } else{
                System.out.println("Нет соединения");
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void createTable() {}
    public void readTable(String nameTable) {
        resultSet = null;
        try {
            String query = "SELECT * FROM " + nameTable;
            statement = connectionDatabase().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void updateTable(String nameTable, Socket server, String data) {
        try {
            String query = "INSERT INTO " + nameTable
                    + "(ip, data) VALUES ('" + server.getInetAddress() + "', " + "'" + data + "');";
            statement = connectionDatabase().createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteTable(String nameTable) {
        try {
            String query = "DELETE FROM " + nameTable + " WHERE id != 0;";
            statement = connectionDatabase().createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
