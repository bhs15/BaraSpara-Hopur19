package hi.group19.BaraSpara;

import hi.group19.BaraSpara.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

interface TransactionRepo extends JpaRepository<Transaction, Long>{
}
