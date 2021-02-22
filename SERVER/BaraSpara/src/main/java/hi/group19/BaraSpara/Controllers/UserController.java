package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Repos.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
class UserController {

    private final UserRepo userRepo;
    UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @GetMapping("/")
    String Home(){
        return "<p>Hello</p>";
    }

}
