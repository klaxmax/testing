package dtos;

/**
 * Created by Klaxmax on 30.01.2017.
 */
public abstract class GeneralDataInterface {
    Integer id = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
