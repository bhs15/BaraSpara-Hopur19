package hi.group19.BaraSpara;

import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepo extends JpaRepository<Transaction, Long>{
}
