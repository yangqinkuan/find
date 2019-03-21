/**
 * FileName: ChildFacade
 * Author:   yangqinkuan
 * Date:     2019-3-20 17:20
 * Description:
 */

package com.ice.find.server.childhandle;

import com.ice.find.message.BusenessMessage;
import com.ice.find.server.childhandle.handlers.ConHandler;

/**
 * 子处理器的门面
 */
public class ChildFacade {

    private ConHandler conHandler = new ConHandler();

    public void childHandle(BusenessMessage busenessMessage,String channelId){
        String eventType = busenessMessage.getEventType();
        switch (eventType){
            case "00001":
                conHandler.handleCon(busenessMessage,channelId);
                break;
            case "10002":
                break;
            case "10001":
                conHandler.handleLogin(busenessMessage,channelId);
                break;
            default:
                break;

        }
    }
}
