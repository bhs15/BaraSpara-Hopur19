package hi.group19.BaraSpara.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hi.group19.BaraSpara.Entities.SavingType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Transaction {
    private @Id @GeneratedValue Long id;
    private int amount;
    private String description;
    private Date date;


    @JsonIgnore
    @ManyToMany(targetEntity = SavingType.class, cascade = CascadeType.MERGE)
    private Set<SavingType> savingTypes = new HashSet<SavingType>();

    Transaction(){}

    public Transaction(int amount){
        this.amount = amount;
        this.description = "";
        this.date = new Date(System.currentTimeMillis());
    }

    public Transaction(int amount, String description){
        this.amount = amount;
        this.description = description;
        this.date = new Date(System.currentTimeMillis());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<SavingType> getSavingTypes() {
        return savingTypes;
    }

    public void setSavingTypes(Set<SavingType> savingTypes) {
        this.savingTypes = savingTypes;
    }

    public void addSavingType(SavingType savingType){
        this.savingTypes.add(savingType);
        savingType.getTransactions().add(this);
    }

    public void removeSavingType(SavingType savingType){
        this.savingTypes.remove(savingType);
        savingType.getTransactions().remove(this);
    }

}
