/**
 * Copyright (C), 2018-2018,
 * FileName: RegistryControler
 * Author:   Administrator
 * Date:     2018/12/5 20:53
 * Description: dispatcher  http registry
 */
package com.ice.find.web.registry;


import com.ice.find.vo.HttpRespVO;
import com.ice.find.vo.registry.ByEmailVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registry")
public class RegistryControler {
    @RequestMapping(value = "/bymail")
    public HttpRespVO registryByMail(ByEmailVO byEmailVO){

        return null;
    }

    @RequestMapping(value = "/getvalidcodebyemail")
    public HttpRespVO getVerifyCodeByEmail(String email){
        //生成验证码

        //保存到redis

        //发送给用户邮箱
        return null;
    }
}