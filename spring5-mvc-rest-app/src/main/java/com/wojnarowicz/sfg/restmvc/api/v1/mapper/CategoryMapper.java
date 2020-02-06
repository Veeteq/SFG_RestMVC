package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.ItemDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.domain.Item;

@Mapper(componentModel="spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    
    CategoryDTO categoryToCategoryDTO(Category category);
    
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    
    Item itemDTOtoItem(ItemDTO itemDTO);
}
