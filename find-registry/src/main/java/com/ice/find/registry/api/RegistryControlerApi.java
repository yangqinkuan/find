/**
 * FileName: RegistryControlerApi
 * Author:   yangqinkuan
 * Date:     2019-1-11 9:01
 * Description: 用于注册调用
 */

package com.ice.find.registry.api;

import com.alibaba.fastjson.JSONObject;
import com.ice.find.registry.dto.email.ByEmailReqDto;
import com.ice.find.registry.dto.email.IsExistedEmailReqDto;
import com.ice.find.registry.dto.email.IsExistedEmailRespDto;
import com.ice.find.registry.service.RegistryImpl;
import com.ice.find.util.codegenerate.ValidCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ice.find.util.vo.httpVo.HttpRespVO;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registry")
public class RegistryControlerApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegistryImpl registryImpl;


    @RequestMapping(value = "/bymail")
    public HttpRespVO registryByMail(ByEmailReqDto byEmailVO){
        return null;
    }

    @RequestMapping(value = "/getvalidcodebyemail")
    public HttpRespVO getVerifyCodeByEmail(String email){
        //生成验证码
        String validCode = ValidCode.get6Code();
        //保存到redis

        //发送给用户邮箱
        return null;
    }

    @RequestMapping(value = "/isexistedemail")
    public @ResponseBody HttpRespVO isExistedEmail(@RequestBody IsExistedEmailReqDto isExistedEmailReqDto){
        logger.info("验证注册邮箱是否存在:{}",isExistedEmailReqDto);
        HttpRespVO httpRespVO = new HttpRespVO();
        if(null == isExistedEmailReqDto || null== isExistedEmailReqDto.getEmail()
        || "".equals(isExistedEmailReqDto.getEmail())){
            httpRespVO.setCode("00001");
            return httpRespVO;
        }
        boolean result = registryImpl.isExistedEmail(isExistedEmailReqDto.getEmail());
        IsExistedEmailRespDto isExistedEmailRespDto = new IsExistedEmailRespDto();
        isExistedEmailRespDto.setResult(result);
        httpRespVO.setData(isExistedEmailRespDto);
        return httpRespVO;
    }

}
