package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;

@ExtendWith(SpringExtension.class)
class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;
    Category category;
    
    @BeforeEach
    void setUp() throws Exception {
        category = new Category();
        category.setId(1L);
        category.setName("Fruit");
    }

    @Test
    void testCategoryToCategoryDTO() {
        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);
        assertNotNull(categoryDTO);
        assertEquals("Fruit", categoryDTO.getName());
    }

}
