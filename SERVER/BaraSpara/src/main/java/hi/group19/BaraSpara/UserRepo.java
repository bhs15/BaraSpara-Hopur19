package hi.group19.BaraSpara;

import hi.group19.BaraSpara.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

interface UserRepo extends JpaRepository<User, Long> {

}
