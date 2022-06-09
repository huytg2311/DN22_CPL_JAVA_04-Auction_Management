package java4.auction_management.service.impl;

import java4.auction_management.entity.user.Provider;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IUserRepository;
import java4.auction_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<User> getAll() {
        return this.iUserRepository.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return this.iUserRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        return this.iUserRepository.save(user);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<User> getAllUser() {
        return this.iUserRepository.getAllUser();
    }




}
