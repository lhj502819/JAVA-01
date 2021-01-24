package netty.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 添加请求头Filter
 * @author lihongjian
 * @since 2021/1/24
 */
public class DefaultReponseHeaderFilter implements ResponseFilter {

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("502819Response" , "customGatWayVisit");
    }
}
