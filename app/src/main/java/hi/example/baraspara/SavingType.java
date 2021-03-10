package hi.example.baraspara;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class SavingType implements Serializable {
    private Long id;
    private int amount;
    private String name;
    private String description;
    private String date;

    public SavingType(Long id, int amount, String name, String description, String date){
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public SavingType(){}

    public void SetSavingType(JSONObject json) throws JSONException {
        this.id = json.getLong("id");
        this.amount = json.getInt("amount");
        this.name = json.getString("name");
        this.description=json.getString("description");
        this.date = json.getString("date");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
