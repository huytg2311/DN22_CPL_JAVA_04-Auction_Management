package java4.auction_management.service.impl;

import java4.auction_management.entity.user.Provider;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IUserRepository;
import java4.auction_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = iUserRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            iUserRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return iUserRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.getAccount().setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        iUserRepository.save(user);
    }


}
