package com.wojnarowicz.sfg.restmvc.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String customerUrl;
}
