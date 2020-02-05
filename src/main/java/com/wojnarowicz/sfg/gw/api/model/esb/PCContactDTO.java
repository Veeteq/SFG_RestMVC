package com.wojnarowicz.sfg.gw.api.model.esb;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Address",
    "ContactDocumentList",
    "ContactMail",
    "ContactPhone",
    "ContactSubtype",
    "Country",
    "DateOfBirth",
    "FirstName",
    "FullName",
    "Gender",
    "LastName",
    "MiddleName",
    "PublicID",
    "Resident"
})
public class PCContactDTO {

    @JsonProperty("Address")
    public PCAddressDTO address;

    @JsonProperty("ContactDocumentList")
    public List<PCContactDocumentDTO> contactDocumentList = new ArrayList<>();

    @JsonProperty("ContactMail")
    public PCContactMailDTO contactMail;

    @JsonProperty("ContactPhone")
    public PCContactPhoneDTO contactPhone;

    @JsonProperty("ContactSubtype")
    public String contactSubtype;

    @JsonProperty("Country")
    public String country;

    @JsonProperty("DateOfBirth")
    public String dateOfBirth;

    @JsonProperty("FirstName")
    public String firstName;

    @JsonProperty("FullName")
    public String fullName;

    @JsonProperty("Gender")
    public String gender;

    @JsonProperty("LastName")
    public String lastName;

    @JsonProperty("MiddleName")
    public String middleName;

    @JsonProperty("PublicID")
    public String publicId;

    @JsonProperty("Resident")
    public String resident;

}