/**
 * FileName: ChildFacade
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:30
 * Description:
 */

package com.ice.find.client.childhandle;

import com.ice.find.client.childhandle.handlers.ConHandler;
import com.ice.find.message.BusenessMessage;

public class ChildFacade {
    private ConHandler conHandler = new ConHandler();
    public void childHandle(BusenessMessage busenessMessage){
        String eventType = busenessMessage.getEventType();
        switch (eventType){
            case "00002":
                conHandler.handleCon(busenessMessage);
                break;
            case "10002":
                break;
            default:
                break;

        }
    }
}
