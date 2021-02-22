package hi.group19.BaraSpara;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TransactionController {
    private final TransactionRepo transactionRepo;
    TransactionController(TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/transactionTest")
    String transactionTest(){
        return "<p>Transaction test</p>";
    }

}