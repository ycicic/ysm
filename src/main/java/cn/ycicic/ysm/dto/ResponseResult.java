package cn.ycicic.ysm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回common参数
 *
 * @author ycc
 */
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -516186144994737582L;
    private int code;
    private String message;
    private T data;

    public ResponseResult(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public ResponseResult() {
        this.code = 200;
        this.message = "success";
    }
}
