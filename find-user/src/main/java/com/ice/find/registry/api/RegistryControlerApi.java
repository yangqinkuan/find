/**
 * FileName: RegistryControlerApi
 * Author:   yangqinkuan
 * Date:     2019-1-11 9:01
 * Description: 用于注册调用
 */

package com.ice.find.registry.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ice.find.registry.dto.email.ByEmailReqDto;
import com.ice.find.registry.dto.email.IsExistedEmailReqDto;
import com.ice.find.registry.dto.email.IsExistedEmailRespDto;
import com.ice.find.registry.service.RegistryImpl;
import com.ice.find.util.common.enums.ErrorEnum;
import com.ice.find.util.exception.CommonException;
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
    public @ResponseBody HttpRespVO registryByMail(@RequestBody ByEmailReqDto byEmailVO){
        logger.info("根据邮箱注册账号:{}", JSON.toJSONString(byEmailVO));
        HttpRespVO httpRespVO = new HttpRespVO();
        try {
            registryImpl.registryByMail(byEmailVO);
            return httpRespVO;
        } catch (CommonException e) {
            logger.info("根据邮箱注册错误:{}",e);
            httpRespVO.setCode(e.getCode());
            httpRespVO.setMessage(e.getMessage());
        }
        return httpRespVO;
    }

    @RequestMapping(value = "/getvalidcodebyemail")
    public @ResponseBody HttpRespVO getVerifyCodeByEmail(@RequestBody IsExistedEmailReqDto isExistedEmailReqDto){
        logger.info("根据邮箱获取验证码:{}",JSONObject.toJSON(isExistedEmailReqDto));
        HttpRespVO httpRespVO = new HttpRespVO();
        if(null == isExistedEmailReqDto || null== isExistedEmailReqDto.getEmail()
                || "".equals(isExistedEmailReqDto.getEmail())){
            httpRespVO.setCode("00001");
            return httpRespVO;
        }
        try {
            registryImpl.getVerifyCodeByEmail(isExistedEmailReqDto.getEmail());
            return httpRespVO;
        } catch (CommonException e) {
            logger.info("根据邮箱获取验证码错误:{}",e);
            httpRespVO.setCode(e.getCode());
            httpRespVO.setMessage(e.getMessage());
        }
        return httpRespVO;
    }

    @RequestMapping(value = "/isexistedemail")
    public @ResponseBody HttpRespVO isExistedEmail(@RequestBody IsExistedEmailReqDto isExistedEmailReqDto){
        logger.info("验证注册邮箱是否存在:{}", JSONObject.toJSON(isExistedEmailReqDto));
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
