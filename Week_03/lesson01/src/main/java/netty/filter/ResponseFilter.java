package netty.filter;


import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 相应过滤器接口
 *
 * @author lihongjian
 * @since 2021/1/24
 */
public interface ResponseFilter {
    void filter(FullHttpResponse response);
}
