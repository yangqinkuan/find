/**
 * FileName: Header
 * Author:   yangqinkuan
 * Date:     2019-1-21 16:06
 * Description: netty消息头
 */

package com.ice.find.message;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public final class Header implements Serializable {
    private static final long serialVersionUID = -7836339040401285482L;
    private int crcCode = 0xabef0101;
    private int length; //消息长度
    private long sessionID;//会话ID
    private byte type;//消息类型
    private byte priority;//消息优先级

    private Map<String,Object> attachment = new HashMap<>();




}
