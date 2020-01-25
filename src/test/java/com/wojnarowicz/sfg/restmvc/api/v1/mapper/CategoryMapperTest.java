package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.ItemDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.domain.Item;

class CategoryMapperTest {

    private static final String CATE_ID = "cat1";
    private static final String FRUIT = "Fruit";
    private static final String ITEM_ID = "item1";
    private static final String BANANA = "Banana";
    
    CategoryMapper mapper = CategoryMapper.INSTANCE;
    Category category;
    
    @BeforeEach
    void setUp() throws Exception {
        category = new Category();
        category.setId(CATE_ID);
        category.setName(FRUIT);
    }

    @Test
    void testCategoryToCategoryDTO() {
        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);
        assertNotNull(categoryDTO);
        assertEquals(FRUIT, categoryDTO.getName());
        assertEquals(CATE_ID, categoryDTO.getId());
    }

    @Test
    void testWithList() {
    	CategoryDTO category1 = new CategoryDTO();
        category1.setId(CATE_ID);
        category1.setName(FRUIT);
        
    	List<ItemDTO> items = new ArrayList<>();
    	
    	ItemDTO item1 = new ItemDTO();
    	item1.setId(ITEM_ID);
    	item1.setName(BANANA);
    	
    	items.add(item1);
    	category1.setItems(items);
    	
    	Category mappedCategory = mapper.categoryDTOToCategory(category1);
    	assertNotNull(mappedCategory);
        assertEquals(FRUIT, mappedCategory.getName());
        assertEquals(CATE_ID, mappedCategory.getId());
        assertEquals(1, mappedCategory.getItems().size());
        
        Item mappedItem = mappedCategory.getItems().get(0);
        assertEquals(BANANA, mappedItem.getName());
        assertEquals(ITEM_ID, mappedItem.getId());
        //assertNotNull(mappedItem.getCategory());
    }
}
