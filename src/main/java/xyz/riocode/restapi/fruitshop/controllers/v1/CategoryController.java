package xyz.riocode.restapi.fruitshop.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryListDTO;
import xyz.riocode.restapi.fruitshop.services.CategoryService;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> listCategories(){
        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String categoryName){
        return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
    }

}
