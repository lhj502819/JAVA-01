package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.dyncamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lihongjian
 * @since 2021/3/6
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContainer.getDataSource();
    }
}
