package java4.auction_management.service.impl;

import java4.auction_management.entity.category.Category;
import java4.auction_management.repository.ICategoryRepository;
import java4.auction_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {

        return iCategoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {

    }


}
