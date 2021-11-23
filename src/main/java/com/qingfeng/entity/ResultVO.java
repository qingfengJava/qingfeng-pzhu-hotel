package com.qingfeng.entity;

/**
 * 通用的视图对象
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public class ResultVO {

    /**
     * 是否成功
     */
    private Boolean success = true;
    /**
     * 返回消息
     */
    private String message;

    /**
     * 附加消息
     */
    private Object data;

    public ResultVO() {
    }

    public ResultVO(String message) {
        this.message = message;
    }

    public ResultVO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultVO(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
