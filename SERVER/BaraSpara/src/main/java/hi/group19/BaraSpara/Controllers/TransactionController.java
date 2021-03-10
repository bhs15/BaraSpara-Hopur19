package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Entities.SavingType;
import hi.group19.BaraSpara.Entities.Transaction;
import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19.BaraSpara.Repos.TransactionRepo;
import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
class TransactionController {
    private final TransactionRepo transactionRepo;
    private final SavingTypeRepo savingTypeRepo;
    private final UserRepo userRepo;

    TransactionController(TransactionRepo transactionRepo,SavingTypeRepo savingTypeRepo,UserRepo userRepo){
        this.transactionRepo = transactionRepo;
        this.savingTypeRepo = savingTypeRepo;
        this.userRepo = userRepo;
    }
    //id af saving type
    @GetMapping("/lookAtTransactions/{id}")
    List<Transaction> transactionTest(@PathVariable Long id){
        SavingType test = savingTypeRepo.getOne(id);
        return new ArrayList<>(test.getTransactions());
    }

    //id af user
    @PostMapping("/saveTransaction/{id}")
    void postTransaction(@PathVariable Long id, @RequestBody Transaction tra)
    {
        tra = transactionRepo.save(tra);
        List<SavingType> sT = new ArrayList<>(userRepo.getOne(id).getSavingTypes());

        for(SavingType var : sT)
        {
            var.addTransaction(tra);
        }

    }

}
