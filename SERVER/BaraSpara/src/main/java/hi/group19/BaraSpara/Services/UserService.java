package hi.group19.BaraSpara.Services;

import hi.group19.BaraSpara.Entities.User;

import java.util.List;

public interface UserService {

    User save(User user);
    void delete (User user);
    User login(String user, String pass);
    List<User> findAll();
}
