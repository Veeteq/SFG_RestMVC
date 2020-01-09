package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;

class CategoryMapperTest {

    private static final long ID = 1L;
    private static final String FRUIT = "Fruit";
    CategoryMapper mapper = CategoryMapper.INSTANCE;
    Category category;
    
    @BeforeEach
    void setUp() throws Exception {
        category = new Category();
        category.setId(ID);
        category.setName(FRUIT);
    }

    @Test
    void testCategoryToCategoryDTO() {
        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);
        assertNotNull(categoryDTO);
        assertEquals(FRUIT, categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId());
    }

}
