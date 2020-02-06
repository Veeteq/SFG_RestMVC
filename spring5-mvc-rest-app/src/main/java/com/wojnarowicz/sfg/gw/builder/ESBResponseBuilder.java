package com.wojnarowicz.sfg.gw.builder;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseDetailDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseDetailsDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseExtendedDetailDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseExtendedDetailsDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseNotificationDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseSummaryDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseSystemDTO;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseSystemsDTO;

public class ESBResponseBuilder {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ESBResponseSummaryDTO summary = new ESBResponseSummaryDTO();
        private ESBResponseSystemsDTO systems = new ESBResponseSystemsDTO();
        private ESBResponseDetailsDTO details = new ESBResponseDetailsDTO();
        private ESBResponseExtendedDetailsDTO extendedDetails = new ESBResponseExtendedDetailsDTO();
        
        public Builder withSummary(String status, String system) {
            ESBResponseSummaryDTO summary = new ESBResponseSummaryDTO();
            summary.setStatus(status);
            summary.setSystem(system);
            this.summary = summary;
            return this;
        }

        public Builder withSystem(String status, String sysName) {
            ESBResponseSystemDTO system = new ESBResponseSystemDTO();
            system.setDetails(new ESBResponseDetailsDTO());
            system.setGeneralStatus(status);
            system.setSysName(sysName);
            this.systems.addSystem(system);
            return this;
        }

        public Builder withDetails(String entityId, String currDate, String component, String status) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            String now = ZonedDateTime.now().format(formatter);
            
            ESBResponseDetailDTO detail = new ESBResponseDetailDTO();
            detail.setComponent(component);
            detail.setEntityId(entityId);
            detail.setDateTime(currDate == null ? now : currDate);
            detail.setStatus(status);
            this.details.addDetail(detail);
            this.systems.getSystem().iterator().next().setDetails(this.details);
            return this;
        }

        public Builder withExtendedDetails(String code, String description, String message) {
            ESBResponseExtendedDetailDTO extendedDetail = new ESBResponseExtendedDetailDTO();
            extendedDetail.setCode(code);
            extendedDetail.setDescription(description);
            extendedDetail.setMessage(message);
            this.extendedDetails.addExtendedDetail(extendedDetail);
            this.systems.getSystem().iterator().next().getDetails().getDetail().iterator().next().setExtendedDetails(this.extendedDetails);
            return this;
        }
        
        public ESBResponseRootDTO build() {
            ESBResponseRootDTO response = new ESBResponseRootDTO();

            ESBResponseNotificationDTO notification = new ESBResponseNotificationDTO();
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
