package xyz.riocode.restapi.fruitshop.services;

import org.springframework.stereotype.Service;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CategoryMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;
import xyz.riocode.restapi.fruitshop.repository.CategoryRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(toList());

    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
