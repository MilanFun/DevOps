package mipt.ptukha.devopsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mipt.ptukha.devopsproject.entity.User;

/**
 *
 * @author aleksey
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
