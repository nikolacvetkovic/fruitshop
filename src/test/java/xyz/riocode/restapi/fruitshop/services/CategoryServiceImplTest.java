package xyz.riocode.restapi.fruitshop.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CategoryMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;
import xyz.riocode.restapi.fruitshop.domain.Category;
import xyz.riocode.restapi.fruitshop.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String NAME = "Fruits";

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void testGetAllCategories(){
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);


        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories();

        assertEquals(3, categoriesDTO.size());

    }

    @Test
    public void testGetCategoryByName(){
        Category category = new Category();
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
    }

}
