package persistence;

import dtos.GeneralDataInterface;
import exceptions.PersistenceException;

import java.util.List;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public interface SimpleCRUD<E extends GeneralDataInterface> {
    public void create(E entity) throws PersistenceException;
    public E getByID(int id) throws PersistenceException;
    public List<E> getAll() throws PersistenceException;
    public void update(E entity) throws PersistenceException;
    public void delete(E entity) throws PersistenceException;
}
