package io.lhj.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.lhj.rpcfx.api.RpcfxRequest;
import io.lhj.rpcfx.api.RpcfxResolver;
import io.lhj.rpcfx.api.RpcfxResponse;
import io.lhj.rpcfx.exception.RpcException;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        Class<?> serviceClass = request.getServiceClass();

        // 作业1：改成泛型和反射
        Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setSuccess(true);
            return response;
        } catch ( Exception e) {

            // 3.Xstream

            // 2.封装一个统一的 RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            RpcException rpcException = new RpcException(e.getLocalizedMessage() , 500);
            response.setException(rpcException);
            response.setSuccess(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
