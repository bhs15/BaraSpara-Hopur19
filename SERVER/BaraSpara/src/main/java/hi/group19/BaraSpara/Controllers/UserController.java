package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Entities.User;
import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
class UserController {

    private final UserRepo userRepo;
    UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @GetMapping("/")
    String Home()
    {
        User tommi = new User("123","123");
        userRepo.save(tommi);
        List<User> tommar = userRepo.findAll();
        return "<p>"+tommar.get(0).getUsername()+"</p>";

    }

}
