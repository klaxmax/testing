package service.GeneralDataServices;

import persistence.GeneralDataInterfaces.ItemDAO;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public class ItemService {

    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }


}
