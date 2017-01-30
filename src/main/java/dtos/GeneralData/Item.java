package dtos.GeneralData;

import dtos.GeneralDataInterface;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public class Item extends GeneralDataInterface {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
