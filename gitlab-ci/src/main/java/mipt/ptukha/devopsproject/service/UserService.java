package mipt.ptukha.devopsproject.service;

import mipt.ptukha.devopsproject.entity.User;
import mipt.ptukha.devopsproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService implements UserRepository {
    private Map<Integer, User> table = new HashMap<>();
    @Override
    public User save(User user) {
        table.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(int id) {
        return table.get(id);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (Map.Entry<Integer, User> map : this.table.entrySet()) {
            list.add(map.getValue());
        }
        return list;
    }

    @Override
    public void deleteById(int id) {
        this.table.remove(id);
    }

    public boolean exist(int id) {
        return this.table.containsKey(id);
    }
}
