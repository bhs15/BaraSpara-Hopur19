package hi.group19.BaraSpara.Entities;

import javax.persistence.*;
import java.util.Date;
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


    @ManyToMany
    @JoinTable(
            name = "SavedIn",
            joinColumns = @JoinColumn(name = "Transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "SavingType_id")
    )
    Set<Transaction> likes;


    SavingType(){}
    SavingType(int amount,String name){
        this.amount = amount;
        this.name = name;
        this.date = new Date(System.currentTimeMillis());
    }

    SavingType(int amount,String name, String description){
        this.amount=amount;
        this.name=name;
        this.description=description;
        this.date = new Date(System.currentTimeMillis());
    }
}
