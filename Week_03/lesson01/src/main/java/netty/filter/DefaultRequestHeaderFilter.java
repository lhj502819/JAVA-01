package netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 添加请求头Filter
 * @author lihongjian
 * @since 2021/1/24
 */
public class DefaultRequestHeaderFilter implements RequestFilter {
    @Override
    public void filter(FullHttpRequest request) {
        request.headers().set("502819" , "customGetWayVisit");
    }
}
