/**
 * FileName: MessageFactory
 * Author:   yangqinkuan
 * Date:     2019-3-20 16:30
 * Description:
 */

package com.ice.find.message;

import com.alibaba.fastjson.JSONObject;
import com.ice.find.client.ClientVariables;
import com.ice.find.util.codegenerate.UUid;
import com.ice.find.util.common.enums.EventTypeEnum;
import com.ice.find.utils.enums.EventType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class MessageFactory {
    private static final String split = "_$";
    public static String getBusenessMessage(EventType eventType, Object o,String instanseId){
        BusenessMessage busenessMessage = new BusenessMessage();
        busenessMessage.setEventType(eventType.getEventType());
        busenessMessage.setMessageId(UUid.getUUID());
        busenessMessage.setBody(o);
        busenessMessage.setInstansId(instanseId);
        return JSONObject.toJSONString(busenessMessage)+split;
    }

    public static ByteBuf getMessageByte(EventType eventType, Object o, String instanseId){
        String msg = getBusenessMessage(eventType,o,instanseId);

        return Unpooled.copiedBuffer(msg.getBytes(CharsetUtil.UTF_8));
    }
}
