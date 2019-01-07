/**
 * Copyright (C), 2018-2019,
 * FileName: BussenessException
 * Author:   Administrator
 * Date:     2019/1/7 23:49
 * Description: 业务异常类
 */
package exception;

public class BusinessException extends RuntimeException{
    private String code;
    private String message;

}