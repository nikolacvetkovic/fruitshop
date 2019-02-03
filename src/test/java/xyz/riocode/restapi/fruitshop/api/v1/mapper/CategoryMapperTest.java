package xyz.riocode.restapi.fruitshop.api.v1.mapper;

import org.junit.Test;
import xyz.riocode.restapi.fruitshop.api.v1.model.CategoryDTO;
import xyz.riocode.restapi.fruitshop.domain.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void testCategoryToCategoryDTO(){
        Category category = new Category();
        category.setName("Nikola");
        category.setId(1L);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }

}