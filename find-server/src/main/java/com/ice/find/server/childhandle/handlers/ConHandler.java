/**
 * FileName: ConHandler
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:11
 * Description:
 */

package com.ice.find.server.childhandle.handlers;

import com.alibaba.fastjson.JSONObject;
import com.ice.find.message.Body;
import com.ice.find.message.BusenessMessage;
import com.ice.find.message.MessageFactory;
import com.ice.find.server.ServerVariables;
import com.ice.find.utils.enums.EventType;
import com.ice.find.utils.enums.dto.LoginReqDto;
import com.ice.find.utils.enums.dto.LoginRespDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;


public class ConHandler {

    /**
     * 处理握手请求
     * @param busenessMessage
     * @param channelId
     */
    public void handleCon(BusenessMessage busenessMessage,String channelId){
        ByteBuf msg = MessageFactory.getMessageByte(EventType.CON_ACK,null,ServerVariables.serverId);
        Channel channel = ServerVariables.channelMap.get(channelId);
        channel.writeAndFlush(msg);
    }

    public void handleLogin(BusenessMessage busenessMessage,String channelId){
        Body bo = busenessMessage.getBody();
        Object data = bo.getData();
        LoginReqDto loginReqDto = JSONObject.parseObject(data.toString(),LoginReqDto.class);
        LoginRespDto loginRespDto = new LoginRespDto();
        Body body = new Body();
        if(loginReqDto.getPassword().equals("123456")){
            loginRespDto.setToken(busenessMessage.getInstansId()+"-token");
            loginRespDto.setChatIp("127.0.0.1");
            loginRespDto.setChatPort("9600");
            body.setCode("00000");
            body.setData(loginRespDto);
        }else {
            body.setCode("99999");
            body.setMessage("密码错误啦");
        }
        ByteBuf msg = MessageFactory.getMessageByte(EventType.LOGIN_ACK,body,ServerVariables.serverId);
        Channel channel = ServerVariables.channelMap.get(channelId);
        channel.writeAndFlush(msg);
    }
}
