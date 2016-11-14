package task5.repositories.userrepository;

import task5.core.User;

import java.util.List;

public interface UserRepository {

    void registerUser(User user);

    List<User> findAllUsers();
}