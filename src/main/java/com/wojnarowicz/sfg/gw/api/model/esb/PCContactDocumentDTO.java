package com.wojnarowicz.sfg.gw.api.model.esb;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DocNumber",
    "DocStartDate",
    "DocumentType",
    "Issued",
    "PublicID",
    "Serie"
})
public class PCContactDocumentDTO {

    @JsonProperty("DocNumber")
    public String docNumber;
 
    @JsonProperty("DocStartDate")
    public LocalDate docStartDate;
    
    @JsonProperty("DocumentType")
    public String documentType;
    
    @JsonProperty("Issued")
    public String issued;
    
    @JsonProperty("PublicID")
    public String publicId;
    
    @JsonProperty("Serie")
    public String serie;

}