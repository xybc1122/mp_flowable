package com.mq.demo.config;

/**
 * 封装返回前端信息
 */
public class ResponseBase {

    private Integer code;
    private String msg;
    private Object data;
    private String type;

    @Override
    public String toString() {
        return "ResponseBase{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", type='" + type + '\'' +
                '}';
    }

    public ResponseBase(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBase(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseBase(Integer code, String msg, String type) {
        super();
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    public ResponseBase() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
