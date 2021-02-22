package hi.group19.BaraSpara;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
