package xyz.riocode.restapi.fruitshop.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryListDTO;
import xyz.riocode.restapi.fruitshop.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public CategoryListDTO listCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryName}")
    public CategoryDTO getCategoryByName(@PathVariable String categoryName){
        return categoryService.getCategoryByName(categoryName);
    }

}
