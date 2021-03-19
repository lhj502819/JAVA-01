package io.lhj.rpcfx.api;

import lombok.Data;


@Data
public class RpcfxRequest {
  private Class<?> serviceClass;
  private String method;
  private Object[] params;
  private String url;
}
