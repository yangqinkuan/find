/**
 * FileName: RegistryImpl
 * Author:   yangqinkuan
 * Date:     2019-1-11 11:43
 * Description:
 */

package com.ice.find.registry.service;


import com.alibaba.fastjson.JSONObject;
import com.ice.find.registry.dto.email.ByEmailReqDto;
import com.ice.find.registry.dto.email.EmailRegistryDto;
import com.ice.find.sql.entity.user.LocalAuth;
import com.ice.find.sql.mapper.user.LocalAuthMapper;
import com.ice.find.util.codegenerate.UUid;
import com.ice.find.util.codegenerate.ValidCode;
import com.ice.find.util.common.enums.EventTypeEnum;
import com.ice.find.util.kafka.KafkaTopicEnum;
import com.ice.find.util.kafka.KafkaMessage;
import com.ice.find.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegistryImpl implements Registry{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Override
    public Boolean isExistedEmail(String email) {
        LocalAuth localAuth = localAuthMapper.selectByEmail(email);
        if(null != localAuth){
            return true;
        }
        return false;
    }

    @Override
    public Boolean registryByMail(ByEmailReqDto byEmailDto) {

        return null;
    }

    @Override
    public Boolean getVerifyCodeByEmail(String email) {
        //生成验证码
        String validCode = ValidCode.get6Code();
        //保存到redis
        redisUtil.set(email,validCode,300);

        KafkaMessage kafkaMessage = new KafkaMessage();
        EmailRegistryDto emailRegistryDto = new EmailRegistryDto();
        emailRegistryDto.setEmail(email);
        emailRegistryDto.setValidCode(validCode);

        kafkaMessage.setEventTypeID(EventTypeEnum.REGISTRY_MAIL_SEND.getEventTypeID());
        kafkaMessage.setMessageID(UUid.getUUID());
        kafkaMessage.setPayload(JSONObject.toJSONString(emailRegistryDto));
        //发送到kafka
        kafkaTemplate.send(KafkaTopicEnum.REGISTRY_MAIL_SEND.getTopic(),JSONObject.toJSONString(kafkaMessage));
        return true;
    }
}
