package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.dyncamic;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源容器
 *
 * @author lihongjian
 * @since 2021/3/6
 */
@Slf4j
public class DataSourceContainer {

    private static final ThreadLocal<String> DS = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        DS.set(dataSource);
        log.info("[设置数据源为：{} 当前线程 {}]", dataSource ,Thread.currentThread().getName());
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        log.info("[当前线程 {} 获取到数据源为：{} ]", Thread.currentThread().getName(), DS.get() );
        return DS.get();
    }

    /**
     * 清空
     */
    public  static void clear() {
        DS.remove();
        log.info("[当前线程 {} 清空数据源 ]", Thread.currentThread().getName() );
    }

}
