package persistence;

import exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public class KeyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyManager.class);
    private static final String ERROR = "Could not create entity.";

    public static int getGeneratedKey(Statement statement) throws PersistenceException {
        try {
            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) return result.getInt(1);
            else {
                LOGGER.error(ERROR);
                throw new PersistenceException();
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR);
            throw new PersistenceException();
        }
    }
}
