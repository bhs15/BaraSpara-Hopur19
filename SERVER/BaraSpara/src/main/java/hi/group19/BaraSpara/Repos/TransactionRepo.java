package hi.group19.BaraSpara.Repos;

import hi.group19.BaraSpara.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{
}
