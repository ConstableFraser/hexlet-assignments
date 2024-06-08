package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(User user, Integer id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public Mono<User> show(Integer id) {
        return userRepository.findById(id);
    }

    public Mono<Void> delete(Integer id) {
        return userRepository.deleteById(id);
    }
    // END
}
