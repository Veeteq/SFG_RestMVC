package com.wojnarowicz.sfg.restmvc.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
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
    
    @ApiModelProperty(name = "First Name", required = true, notes = "First name of the customer")
    private String firstName;
    private String lastName;
    private String customerUrl;
}
