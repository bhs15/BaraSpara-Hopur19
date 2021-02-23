package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19. BaraSpara.Entities.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Set;

@RestController
public class SavingTypeController {

    private final SavingTypeRepo savingTypeRepo;
    SavingTypeController(SavingTypeRepo savingTypeRepo){
        this.savingTypeRepo = savingTypeRepo;
    }

    @GetMapping("/savingTypeTest")
    String savingTypeTest(){
        savingTypeRepo.save(new SavingType(5000,"Test"));
        List<SavingType> sT = savingTypeRepo.findAll();
        sT.get(0).addTransaction(new Transaction(100,"TestTransaction"));
        savingTypeRepo.save(sT.get(0));
        sT = savingTypeRepo.findAll();
        Object tra[] = sT.get(0).getTransactions().toArray();
        Transaction trans = (Transaction)tra[0];
        return "<p>"+trans.getAmount()+"</p>";
    }

}
