package org.example.todo.util;

/**
 * @program: todolist
 * @ClassName ResultJson
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 18:16
 * @Version 1.0
 **/
public class ResultJson {

    private Integer status; // 返回结果状态1表示成功，0表示失败
    private String code; // 返回状态码
    private String info; // 返回的信息
    private Object data; // 返回结果集

    public ResultJson(Integer Status, String Code, String Info, Object Data) {
        super();
        status = Status;
        code = Code;
        info = Info;
        data = Data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
