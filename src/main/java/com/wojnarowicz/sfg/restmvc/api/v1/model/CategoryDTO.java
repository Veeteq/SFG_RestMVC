package com.wojnarowicz.sfg.restmvc.api.v1.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String name;
    
    private List<ItemDTO> items = new ArrayList<>();
}
