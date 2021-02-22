package hi.group19.BaraSpara.Controllers;

import hi.group19.BaraSpara.Repos.SavingTypeRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SavingTypeController {

    private final SavingTypeRepo savingTypeRepo;
    SavingTypeController(SavingTypeRepo savingTypeRepo){
        this.savingTypeRepo = savingTypeRepo;
    }

    @GetMapping("/savingTypeTest")
    String savingTypeTest(){
        return "<p>SavingTypeTest</p>";
    }

}
