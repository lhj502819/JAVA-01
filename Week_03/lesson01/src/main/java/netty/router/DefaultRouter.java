package netty.router;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 默认路由算法
 *
 * @author lihongjian
 * @since 2021/1/24
 */
public class DefaultRouter implements Router{

    /**
     * key：服务名称
     * value：服务IP
     */
    Map<String, List<String>> serverMap;

    public DefaultRouter(Map<String, List<String>> serverMap) {
        this.serverMap = serverMap;
    }

    /**
     * 服务选择
     *
     * @param uri 请求URI
     * @return 服务地址
     */
    @Override
    public String route(String uri) {
        //根据服务名称选择出对应服务地址列表
        String serverName = uri.split("/")[1];
        List<String> serverList = serverMap.get(serverName);
        //随机选择一个服务
        Random random = new Random();
        return serverList.get(random.nextInt(serverList.size()));
    }
}
