package com.wojnarowicz.sfg.gw.batch;

import javax.persistence.Query;

import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class JpaQueryProviderImpl<T> extends AbstractJpaQueryProvider {

    private Class<T> clazz;
    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public void setEntityClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Query createQuery() {
        return getEntityManager().createNamedQuery(query, clazz);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(StringUtils.hasText(query), "Query cannot be empty");
        Assert.notNull(clazz, "Entity class cannot be NULL");
    }
}
