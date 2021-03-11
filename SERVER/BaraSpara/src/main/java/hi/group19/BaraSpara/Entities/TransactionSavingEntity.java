package hi.group19.BaraSpara.Entities;

import javax.persistence.Entity;
import java.util.List;

public class TransactionSavingEntity {
    int amount;
    List<Long> ids;

    public TransactionSavingEntity(){}
    public TransactionSavingEntity(int amount, List<Long> ids){
        this.amount = amount;
        this.ids = ids;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
