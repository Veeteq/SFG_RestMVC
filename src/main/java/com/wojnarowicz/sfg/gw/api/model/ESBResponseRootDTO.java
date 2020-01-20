package com.wojnarowicz.sfg.gw.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Notification"
})
public class ESBResponseRootDTO {
    
    @JsonProperty(value = "Notification")
    private ESBResponseNotificationDTO notification;
}
