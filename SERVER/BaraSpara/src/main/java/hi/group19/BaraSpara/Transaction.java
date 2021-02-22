package hi.group19.BaraSpara;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Transaction {
    private @Id @GeneratedValue Long id;
    private int amount;
    private String description;
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "SavedIn",
            joinColumns = @JoinColumn(name = "Transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "SavingType_id")
    )

    private Set<SavingType> savedIn;

    Transaction(){}

    Transaction(int amount){
        this.amount = amount;
        this.description = "";
        this.date = new Date(System.currentTimeMillis());
    }

    Transaction(int amount, String description){
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

    public Set<SavingType> getSavedIn() {
        return savedIn;
    }

    public void setSavedIn(Set<SavingType> savedIn) {
        this.savedIn = savedIn;
    }
}
