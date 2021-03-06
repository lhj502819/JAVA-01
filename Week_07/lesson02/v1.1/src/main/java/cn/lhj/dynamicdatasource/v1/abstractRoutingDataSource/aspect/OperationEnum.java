package cn.lhj.dynamicdatasource.v1.abstractRoutingDataSource.aspect;

/**
 * 数据库操作类型枚举
 *
 * @author lihongjian
 * @since 2021/3/6
 */
public enum OperationEnum {

    /**
     * 读操作
     */
    READ(1),
    /**
     * 写操作
     */
    WRITE(0);
    private Integer value;

    OperationEnum(Integer value) {
        this.value = value;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
