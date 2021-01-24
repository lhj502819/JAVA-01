package netty;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import netty.filter.RequestFilter;
import netty.filter.ResponseFilter;

/**
 * @author lihongjian
 * @since 2021/1/24
 */
public interface Interceptor {

    default void preProcess(FullHttpRequest fullHttpRequest, RequestFilter requestFilter){
        requestFilter.filter(fullHttpRequest);
    }

    default void postProcess(FullHttpResponse fullHttpResponse, ResponseFilter responseFilter){
        responseFilter.filter(fullHttpResponse);
    }
}
