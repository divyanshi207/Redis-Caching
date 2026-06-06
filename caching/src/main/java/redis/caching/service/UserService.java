package redis.caching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.caching.entity.User;
import redis.caching.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users" , key = "#id")
    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // or throw an exception if not found
    }

    @CachePut(value = "users",key = "#user.id")
    public User addUser(User user) {
        userRepository.save(user);
        return  user;
    }

    @CacheEvict(value = "users", key = "#id")
    public String removeUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {userRepository.deleteById(id);}
        else {return "User not found";}
        return "User removed";
    }
}
