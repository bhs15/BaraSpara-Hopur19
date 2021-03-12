package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19. BaraSpara.Entities.*;
import hi.group19.BaraSpara.Repos.TransactionRepo;
import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("savingTypes")
public class SavingTypeController {
    private final UserRepo userRepo;
    private final SavingTypeRepo savingTypeRepo;
    private  final TransactionRepo transactionRepo;
    SavingTypeController(SavingTypeRepo savingTypeRepo, UserRepo userRepo,TransactionRepo transactionRepo){

        this.savingTypeRepo = savingTypeRepo;
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/{id}")
    List<SavingType> getSavingTypes(@PathVariable Long id){
        try{
            User re = userRepo.getOne(id);
            List<SavingType> sT = new ArrayList<>(re.getSavingTypes());
            if(sT.isEmpty()){
                sT.add(new SavingType(-1,"EMPTY"));
            }
            return sT;
        }catch(Exception e){
            List<SavingType> sT = new ArrayList<>();
            sT.add(new SavingType(-1,"ERROR"));;
            return sT;
        }
    }

    @PostMapping("/addSavingType/{id}")
    User postSavingType(@PathVariable Long id, @RequestBody SavingType savingType){
        try{
            User re = userRepo.getOne(id);
            savingType = new SavingType(savingType.getAmount(),savingType.getName(),savingType.getDescription());
            savingType = savingTypeRepo.save(savingType);
            re.getSavingTypes().add(savingType);
            re =userRepo.save(re);
            return re;
        }catch (Exception e){
            //TODO
            return null;
        }
    }

    @DeleteMapping("/remove/{user}/{id}")
    public User removeSavingType(@PathVariable Long user, @PathVariable Long id){
        try{
            SavingType st = savingTypeRepo.getOne(id);
            List<Transaction> transactions = new ArrayList<>(st.getTransactions());

            for(Transaction var: transactions){
                var.removeSavingType(st);
                transactionRepo.save(var);
            }

            User us = userRepo.getOne(user);
            us.deleteSavingType(st);
            us=userRepo.save(us);
            savingTypeRepo.delete(st);
            return us;
        }
        catch (Exception e){
            return null;
        }
    }
}

/*
savingTypeRepo.save(new SavingType(5000,"Test"));
List<SavingType> sT = savingTypeRepo.findAll();
sT.get(0).addTransaction(new Transaction(100,"TestTransaction"));
savingTypeRepo.save(sT.get(0));
sT = savingTypeRepo.findAll();
Object tra[] = sT.get(0).getTransactions().toArray();
Transaction trans = (Transaction)tra[0];
*/
