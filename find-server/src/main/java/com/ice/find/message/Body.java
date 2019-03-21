/**
 * FileName: Body
 * Author:   yangqinkuan
 * Date:     2019-3-21 17:29
 * Description:
 */

package com.ice.find.message;

import java.io.Serializable;

public class Body implements Serializable {

    private static final long serialVersionUID = 5872038444578698948L;
    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
