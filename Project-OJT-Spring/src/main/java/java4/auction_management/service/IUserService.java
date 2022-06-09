package java4.auction_management.service;

import java4.auction_management.entity.user.Provider;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserService extends IService<User, String>{

    List<User> getAllUser();


}
