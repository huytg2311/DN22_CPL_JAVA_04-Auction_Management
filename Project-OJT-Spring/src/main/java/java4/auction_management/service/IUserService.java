package java4.auction_management.service;

import java4.auction_management.entity.user.User;

import java.util.List;

public interface IUserService extends IService<User, String>{
    List<User> getAllUser();
}
