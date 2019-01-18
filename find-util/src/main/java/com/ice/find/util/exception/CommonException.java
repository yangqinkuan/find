/**
 * Copyright (C), 2018-2019,
 * FileName: BussenessException
 * Author:   Administrator
 * Date:     2019/1/7 23:49
 * Description: 业务异常类
 */
package com.ice.find.util.exception;

import com.ice.find.util.common.enums.ErrorEnum;

public class CommonException extends BaseException{
    private static final long serialVersionUID = 204634425753340290L;
    public CommonException(String code) {
        super(code, (String)null, (Object[])null, (Throwable)null);
    }

    public CommonException(String code, String message) {
        super(code, message, (Object[])null, (Throwable)null);
    }

    public CommonException(String code, String message, Object[] values) {
        super(code, message, values, (Throwable)null);
    }

    public CommonException(String code, String message, Throwable cause) {
        super(code, message, (Object[]) null, cause);
    }


    public CommonException(String code, String message, Object[] values, Throwable cause) {
        super(code, message, values, cause);
    }

    public CommonException(ErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getDescription());
    }
}