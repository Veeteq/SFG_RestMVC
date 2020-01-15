package com.wojnarowicz.sfg.gw.api.builder;

import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseDetail;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseDetails;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseExtendedDetail;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseExtendedDetails;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseNotification;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseSummary;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseSystem;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseSystems;

public class ESBResponseBuilder {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ESBResponseSummary summary = new ESBResponseSummary();
        private ESBResponseSystems systems = new ESBResponseSystems();
        private ESBResponseDetails details = new ESBResponseDetails();
        private ESBResponseExtendedDetails extendedDetails = new ESBResponseExtendedDetails();
        
        public Builder withSummary(String status, String system) {
            ESBResponseSummary summary = new ESBResponseSummary();
            summary.setStatus(status);
            summary.setSystem(system);
            this.summary = summary;
            return this;
        }

        public Builder withSystem(String status, String sysName) {
            ESBResponseSystem system = new ESBResponseSystem();
            system.setDetails(new ESBResponseDetails());
            system.setGeneralStatus(status);
            system.setSysName(sysName);
            this.systems.addSystem(system);
            return this;
        }

        public Builder withDetails(String correlationID, String currDate, String component, String status) {
            ESBResponseDetail detail = new ESBResponseDetail();
            detail.setComponent(component);
            detail.setDateTime(currDate);
            detail.setStatus(status);
            this.details.addDetail(detail);
            this.systems.getSystem().iterator().next().setDetails(this.details);
            return this;
        }

        public Builder withExtendedDetails(String code, String description, String message) {
            ESBResponseExtendedDetail extendedDetail = new ESBResponseExtendedDetail();
            extendedDetail.setCode(code);
            extendedDetail.setDescription(description);
            extendedDetail.setMessage(message);
            this.extendedDetails.addExtendedDetail(extendedDetail);
            this.systems.getSystem().iterator().next().getDetails().getDetail().iterator().next().setExtendedDetails(this.extendedDetails);
            return this;
        }
        
        public ESBResponseRootDTO build() {
            ESBResponseRootDTO response = new ESBResponseRootDTO();

            ESBResponseNotification notification = new ESBResponseNotification();
            notification.setSummary(this.summary);
            notification.setSystems(this.systems);
            response.setNotification(notification);

            return response;
        }
    }

    public static Builder build() {
        return new Builder();
    }    
}
