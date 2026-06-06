package redis.caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import redis.caching.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
//    User findById(int id);
}
