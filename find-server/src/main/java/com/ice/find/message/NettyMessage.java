/**
 * FileName: NettyMessage
 * Author:   yangqinkuan
 * Date:     2019-1-21 16:05
 * Description: Netty消息
 */

package com.ice.find.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class NettyMessage implements Serializable {
    private static final long serialVersionUID = 2671859527265663573L;
    private Header header;//消息头
    private Object body;//消息体
}
