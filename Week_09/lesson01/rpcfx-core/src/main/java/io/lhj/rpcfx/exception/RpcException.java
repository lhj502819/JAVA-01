package io.lhj.rpcfx.exception;

import lombok.*;

/**
 * 自定义RPC异常
 *
 * @author lihongjian
 * @since 2021/3/18
 */
@Data
@ToString
public class RpcException{

    public RpcException(String message, Integer code){
        this.message = message;
        this.code = code;
    }

    public String message;

    public Integer code;

}
