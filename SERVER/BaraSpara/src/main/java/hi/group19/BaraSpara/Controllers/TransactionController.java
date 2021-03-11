package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Entities.SavingType;
import hi.group19.BaraSpara.Entities.Transaction;
import hi.group19.BaraSpara.Entities.TransactionSavingEntity;
import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19.BaraSpara.Repos.TransactionRepo;
import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("transactions")
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
    @PostMapping("/saveTransaction")
    void postTransaction(@RequestBody TransactionSavingEntity jason){

        Transaction tra = transactionRepo.save(new Transaction(jason.getAmount()));
        List<Long> ids = new ArrayList<>();
        List<Long> IDS = jason.getIds();

        System.out.println(tra.toString());

        for(int i=0;i<IDS.size();i++){
            ids.add(IDS.get(i));
        }

        for(Long ID : ids)
        {
            SavingType sT = savingTypeRepo.getOne(ID);
            sT.addTransaction(tra);
            savingTypeRepo.save(sT);
        }

    }

}
