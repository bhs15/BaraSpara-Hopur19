package hi.example.baraspara;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private List<SavingType> st = new ArrayList<>();
    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SavingType> getSt() {
        return st;
    }

    public void setSt(List<SavingType> st) {
        this.st = st;
    }

    public void setUser(JSONObject json) throws JSONException {
        this.id=json.getLong("id");
        this.password = json.getString("password");
        this.username = json.getString("username");

        JSONArray arr = json.getJSONArray("savingTypes");
        int len = arr.length();
        for(int i=0;i<len;i++){
            SavingType temp = new SavingType();
            temp.SetSavingType(arr.getJSONObject(i));
            st.add(temp);
        }

    }
}
