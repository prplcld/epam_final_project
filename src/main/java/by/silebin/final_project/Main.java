package by.silebin.final_project;

import by.silebin.final_project.model.pool.ConnectionPool;
import by.silebin.final_project.model.pool.ProxyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (ProxyConnection proxyConnection = connectionPool.getConnection();
             Connection connection = proxyConnection.getConnection()) {

            String sql = "select id, name from roles";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
