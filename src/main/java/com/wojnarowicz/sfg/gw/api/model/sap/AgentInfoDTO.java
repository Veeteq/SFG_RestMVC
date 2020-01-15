package com.wojnarowicz.sfg.gw.api.model.sap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"AgentCode",
"AgentRoles"
})
public class AgentInfoDTO {
    
    @JsonProperty("AgentCode")
    public String agentCode;
    
    @JsonProperty("AgentRoles")
    public List<Integer> agentRoles = null;
}
