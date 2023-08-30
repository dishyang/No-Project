package com.example.noProject.criterion.data;


import com.example.noProject.constants.RequestResultConstants;

public class BaseResponse {

    public int code;
    public Object data;
    public String message;

    public BaseResponse() {
        code = RequestResultConstants.SERVICE_FAIL_CODE;
        data = null;
        message = RequestResultConstants.SERVICE_FAIL_MSG;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
