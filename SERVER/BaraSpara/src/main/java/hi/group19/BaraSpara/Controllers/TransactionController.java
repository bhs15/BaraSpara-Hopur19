package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Repos.TransactionRepo;
import org.springframework.web.bind.annotation.GetMapping;
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
