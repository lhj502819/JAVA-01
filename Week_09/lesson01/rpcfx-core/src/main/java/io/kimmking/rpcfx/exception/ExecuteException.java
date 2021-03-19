package io.kimmking.rpcfx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/3/18
 */
@AllArgsConstructor
@ToString
@Data
public class ExecuteException extends Exception {
    public String message;

    public Integer code;


}
