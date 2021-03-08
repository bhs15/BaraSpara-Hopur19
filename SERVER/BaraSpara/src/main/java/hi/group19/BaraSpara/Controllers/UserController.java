package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Entities.SavingType;
import hi.group19.BaraSpara.Entities.Transaction;
import hi.group19.BaraSpara.Entities.User;
import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19.BaraSpara.Repos.TransactionRepo;
import hi.group19.BaraSpara.Repos.UserRepo;
import hi.group19.BaraSpara.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
class UserController {

    private final UserService userRepo;
    private final SavingTypeRepo savingTypeRepo;
    private final TransactionRepo transactionRepo;
    UserController(UserService userRepo, SavingTypeRepo savingTypeRepo,TransactionRepo transactionRepo){
        this.userRepo = userRepo;
        this.savingTypeRepo = savingTypeRepo;
        this.transactionRepo = transactionRepo;
    }

    @PostMapping("/login")
    User login(@RequestBody User user)
    {
        return userRepo.login(user.getUsername(),user.getPassword());
    }


    @GetMapping("/generate")
    User Generate()
    {
        User tommi = new User("123","123");
        tommi =userRepo.save(tommi);
        for(int i=0;i<10;i++){
            SavingType test = new SavingType(100,"kenny");
            tommi.getSavingTypes().add(test);
            savingTypeRepo.save(test);
            for(int j=0;j<20;j++)
            {
                Transaction ts = new Transaction(1*2,""+2);
                ts = transactionRepo.save(ts);
                test.addTransaction(ts);
            }
        }
        List<User> tommar = userRepo.findAll();
        return tommar.get(0);

    }

}
