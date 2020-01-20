package com.wojnarowicz.sfg.gw.api.builder;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoIssuance;

public class BsoIssuanceBuilder {

    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Agent agent;
        private BsoDocument bsoDocument;
        
        public Builder withAgent(Agent agent) {
            this.agent = agent;
            return this;
        }

        public Builder withBso(BsoDocument bsoDocument) {
            this.bsoDocument = bsoDocument;
            return this;
        }

        public BsoIssuance build() {
            BsoIssuance bsoIssuance = new BsoIssuance();
            bsoIssuance.setAgent(this.agent);
            bsoIssuance.setBsoDocument(this.bsoDocument);
            bsoIssuance.setContract(this.bsoDocument.getContract());
            bsoIssuance.setIssueDate(this.bsoDocument.getUpdateDate());
            bsoIssuance.setStatus(this.bsoDocument.getStatus());
            return bsoIssuance;
        }
    }
}
