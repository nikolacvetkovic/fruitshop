package xyz.riocode.restapi.fruitshop.services;

import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
