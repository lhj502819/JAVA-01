package io.kimmking.rpcfx.api;

import io.kimmking.rpcfx.exception.RpcException;
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
