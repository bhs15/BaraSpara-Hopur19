package hi.group19.BaraSpara.Entities;

import hi.group19.BaraSpara.Entities.SavingType;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
    private @Id @GeneratedValue Long id;

    @OneToMany
    Set<SavingType> savingTypes;

    private String username;

    private String password;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

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

    public Set<SavingType> getSavingTypes() {
        return savingTypes;
    }

    public void setSavingTypes(Set<SavingType> savingTypes) {
        this.savingTypes = savingTypes;
    }
}



