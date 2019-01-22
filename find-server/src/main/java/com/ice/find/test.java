/**
 * FileName: test
 * Author:   yangqinkuan
 * Date:     2019-1-22 9:23
 * Description:
 */

package com.ice.find;

import com.ice.find.message.NettyMessage;
import lombok.Data;

public class test {


    public static void main(String[] args) {
        NettyMessage nettyMessage = new NettyMessage();
        User user = new User();
        user.setName("asd");
        user.setPhone("sds");
        nettyMessage.setBody(user);
        Object o = nettyMessage.getBody();
        
    }
}
@Data
class User{
    String name;
    String phone;

}
