package org.jongo.query;

/**
 * TODO description
 * Created by liusz on 15-1-13-下午2:35
 */
public abstract class QueryFactoryDecorator implements QueryFactory {

    private QueryFactory queryFactory;

    public QueryFactoryDecorator(QueryFactory queryFactory) {
            this.queryFactory = queryFactory;
    }

    public abstract Query createQuery(String query, Object... parameters);

    public QueryFactory getQueryFactory() {
        return queryFactory;
    }
}
