/**
 * FileName: ConHandler
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:31
 * Description:
 */

package com.ice.find.client.childhandle.handlers;

import com.alibaba.fastjson.JSONObject;
import com.ice.find.client.ClientVariables;
import com.ice.find.message.Body;
import com.ice.find.message.BusenessMessage;
import com.ice.find.message.MessageFactory;
import com.ice.find.utils.enums.EventType;
import com.ice.find.utils.enums.dto.LoginRespDto;


public class ConHandler {
    public void handleCon(BusenessMessage busenessMessage){
        String msg = MessageFactory.getBusenessMessage(EventType.CON_ACK,null, ClientVariables.clientId);
        System.out.println("收到服务器的响应");
        //channel.writeAndFlush(msg);
    }

    public void handleLogin(BusenessMessage busenessMessage){
        Body body = busenessMessage.getBody();
        if(body.getCode().equals("00000")){
            LoginRespDto loginRespDto = JSONObject.parseObject(body.getData().toString(),LoginRespDto.class);

            System.out.println("登陆成功+token为:"+loginRespDto.getToken());
        }else if (body.getCode().equals("99999")){
            System.out.println("登陆失败:"+body.getMessage());
        }
        //channel.writeAndFlush(msg);
    }
}
