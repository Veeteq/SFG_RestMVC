package com.wojnarowicz.sfg.gw.domain;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class CustomSequenceGenerator  extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        
        if(object instanceof NumericEntity) {
            NumericEntity persistent = (NumericEntity) object;
            if(persistent.getId() != null && persistent.getId() >= 0) {
                return persistent.getId();
            }
        }
        return super.generate(session, object);
    }
}
