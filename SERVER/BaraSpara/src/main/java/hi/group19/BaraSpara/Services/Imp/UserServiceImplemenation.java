package hi.group19.BaraSpara.Services.Imp;

import hi.group19.BaraSpara.Entities.User;
import hi.group19.BaraSpara.Repos.UserRepo;
import hi.group19.BaraSpara.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplemenation implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User save(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepo.delete(user);
    }

    @Override
    public User login(String user, String pass) {
        List<User> us = userRepo.findAll();

        for(int i=0;i<us.size();i++)
        {
            if(us.get(i).getPassword().equals(pass) && us.get(i).getUsername().equals(user)) return us.get(i);
        }
        return new User("ERROR","ERROR");
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
