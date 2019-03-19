/**
 * FileName: AcceptorIdleStateTrigger
 * Author:   yangqinkuan
 * Date:     2019-3-18 12:45
 * Description:
 */

package com.ice.find.HeartTest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent)evt).state();
            if(state == IdleState.READER_IDLE){
                throw new Exception("idle exception");
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}
