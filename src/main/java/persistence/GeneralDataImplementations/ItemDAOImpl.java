package persistence.GeneralDataImplementations;

import dtos.GeneralData.Item;
import exceptions.ConnectionException;
import exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.ConnectionManager;
import persistence.GeneralDataInterfaces.ItemDAO;
import persistence.KeyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public class ItemDAOImpl implements ItemDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDAOImpl.class);

    /*Statements*/
    private static final String CREATE = "insert into item (description) values (?);";
    private static final String GET_ALL = "select * from items;";
    private static final String UPDATE = "update item SET description = ? WHERE id = ?;";
    /*Statements*/


    private static Connection con;

    public ItemDAOImpl(Connection con){
        this.con = con;
    }


    public void create(Item entity) throws PersistenceException {
        if (entity == null) {
            LOGGER.error("Item cannot be Null!");
            throw new PersistenceException();
        }

        //TODO: check if entity Exists.

        try (PreparedStatement statement = con.prepareStatement(CREATE)) {

            statement.setString(1, entity.getDescription());

            statement.execute();
            int id = KeyManager.getGeneratedKey(statement);
            entity.setId(id);

        } catch (SQLException e) {
            LOGGER.error("Error by creating Item: " + e.getMessage());
            throw new PersistenceException();
        }
    }

    public Item getByID(int id) throws PersistenceException {
        return null;
    }

    public List<Item> getAll() throws PersistenceException {
        try (PreparedStatement statement = con.prepareStatement(GET_ALL)) {

            return getItemList(statement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error("Error by getting all Items: " + e.getMessage());
            throw new PersistenceException();
        }
    }

    public void update(Item entity) throws PersistenceException {
        //TODO: Validate item.

        try (PreparedStatement statement = con.prepareStatement(UPDATE)) {

            statement.setString(1, entity.getDescription());
            statement.setInt(2, entity.getId());

            statement.execute();

        } catch (SQLException e) {
            LOGGER.error("Error by Updating item: " + e.getMessage());
            throw new PersistenceException();
        }
    }

    public void delete(Item entity) throws PersistenceException {

    }

    private List<Item> getItemList(ResultSet result) throws SQLException {
        List<Item> items = new ArrayList<>();
        while (result.next()) {
            Item item = new Item();

            item.setId(result.getInt("id"));
            item.setDescription(result.getString("description"));

            items.add(item);
        }
        return items;
    }
}
