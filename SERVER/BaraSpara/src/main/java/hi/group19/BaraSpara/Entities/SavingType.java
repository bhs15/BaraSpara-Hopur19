package hi.group19.BaraSpara.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SavingType {
    private @Id
    @GeneratedValue
    Long id;
    private int amount;
    private String name;
    private String description;
    private Date date;

    @JsonIgnore
    @ManyToMany(targetEntity = Transaction.class,cascade = CascadeType.MERGE)
    private Set<Transaction> transactions = new HashSet<Transaction>();

    SavingType(){}

    public SavingType(int amount, String name){
        this.amount = amount;
        this.name = name;
        this.date = new Date(System.currentTimeMillis());
    }

    public SavingType(int amount,String name, String description){
        this.amount=amount;
        this.name=name;
        this.description=description;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        transaction.getSavingTypes().add(this);
    }

    public void removeTransaction(Transaction transaction){
        this.transactions.remove(transaction);
        transaction.getSavingTypes().remove(transaction);
    }


}
