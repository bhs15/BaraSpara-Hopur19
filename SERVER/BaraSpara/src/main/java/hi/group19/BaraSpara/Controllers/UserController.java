package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Entities.SavingType;
import hi.group19.BaraSpara.Entities.User;
import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
class UserController {

    private final UserRepo userRepo;
    private final SavingTypeRepo savingTypeRepo;

    UserController(UserRepo userRepo, SavingTypeRepo savingTypeRepo){
        this.userRepo = userRepo;
        this.savingTypeRepo = savingTypeRepo;
    }


    @GetMapping("/")
    User Home()
    {
        User tommi = new User("123","123");
        SavingType test = new SavingType(100,"kenny");
        tommi.getSavingTypes().add(test);
        savingTypeRepo.save(test);
        userRepo.save(tommi);
        List<User> tommar = userRepo.findAll();
        return tommar.get(0);
    }

}
