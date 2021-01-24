package netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 请求过滤器接口
 *
 * @author lihongjian
 * @since 2021/1/24
 */
public interface RequestFilter {
    void filter(FullHttpRequest request);
}
