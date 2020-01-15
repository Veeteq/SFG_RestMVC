package com.wojnarowicz.sfg.gw.api.builder;

import com.wojnarowicz.sfg.gw.domain.Contract;

public class ContractBuilder {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String series;
        private String number;

        public Builder withSeries(String series) {
            this.series = series;
            return this;
        }
        
        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Contract build() {
            Contract contract = new Contract();
            contract.setSeries(this.series);
            contract.setNumber(this.number);
            return contract;
        }
    }
    
    public static Builder build() {
        return new Builder();
    }        
}
