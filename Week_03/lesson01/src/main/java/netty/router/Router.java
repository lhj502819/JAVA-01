package netty.router;

/**
 * 路由
 * @author lihongjian
 * @since 2021/1/24
 */
public interface Router {
    public String route(String uri);
}
