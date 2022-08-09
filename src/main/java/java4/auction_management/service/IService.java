package java4.auction_management.service;

import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface IService<T, K> {

    List<T> getAll();

    Optional<T> getById(K id);

    T save(T entity);

    void deleteById(K id);


}
