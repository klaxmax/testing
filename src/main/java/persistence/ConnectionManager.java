package persistence;

import exceptions.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public class ConnectionManager {

    private static Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    private Connection con;
    private String url;

    public void openConnection(String url,String username,String password) throws ConnectionException {
        LOGGER.info("Trying to open Connection for: {}, {}, {}",url,username,password);
        if(url == null || url == ""){
            LOGGER.error("URL for Database cannot be null");
            throw new ConnectionException();
        }

        if(username == null || username == ""){
            LOGGER.error("Username for Database cannot be null");
            throw new ConnectionException();
        }

        if(password == null){
            LOGGER.error("Password for Database cannot be null");
            throw new ConnectionException();
        }

        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(url,username,password);
            LOGGER.info("Connection established");
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Cannot connect to Database: " + e.getMessage());
            throw new ConnectionException();
        }

    }

    public void closeConnection() throws ConnectionException {
        LOGGER.info("Trying to close Connection.");
        try {
            con.close();
            LOGGER.info("Connection closed.");
        } catch (SQLException e) {
            LOGGER.error("Cannot close Connection.");
            throw new ConnectionException();
        }
    }

    public Connection getConnection() throws ConnectionException {
        LOGGER.info("Trying to retrieve Connection.");
        try {
            if(con == null || !con.isClosed()){
                LOGGER.info("Returning Connection.");
                return con;
            }
        } catch (SQLException e) {
            LOGGER.error("Error by trying to receive Connection.");
            throw new ConnectionException();
        }

        LOGGER.error("Connection is not established.");
        throw new ConnectionException();
    }
}
