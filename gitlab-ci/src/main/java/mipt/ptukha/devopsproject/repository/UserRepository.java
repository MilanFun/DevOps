package mipt.ptukha.devopsproject.repository;


import mipt.ptukha.devopsproject.entity.User;

import java.util.List;

/**
 *
 * @author aleksey
 */
public interface UserRepository {
    User save(User user);
    User findById(int id);
    List<User> findAll();
    void deleteById(int id);

}
