package io.lhj.rpcfx.api;

import io.lhj.rpcfx.exception.RpcException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RpcfxResponse {
    private Object result;
    private boolean success;
    private RpcException exception;
}
