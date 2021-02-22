package hi.group19.BaraSpara.Repos;

import hi.group19.BaraSpara.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
