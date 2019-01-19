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
import com.ice.find.sql.entity.user.UserInfo;
import com.ice.find.sql.mapper.user.LocalAuthMapper;
import com.ice.find.sql.mapper.user.UserInfoMapper;
import com.ice.find.util.codegenerate.UUid;
import com.ice.find.util.codegenerate.ValidCode;
import com.ice.find.util.common.enums.ErrorEnum;
import com.ice.find.util.common.enums.EventTypeEnum;
import com.ice.find.util.exception.CommonException;
import com.ice.find.util.kafka.KafkaTopicEnum;
import com.ice.find.util.kafka.KafkaMessage;
import com.ice.find.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class RegistryImpl implements Registry{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean isExistedEmail(String email) {
        LocalAuth localAuth = localAuthMapper.selectByEmail(email);
        if(null != localAuth){
            return true;
        }
        return false;
    }

    @Override
    public void registryByMail(ByEmailReqDto byEmailDto) {
        if(!byEmailDto.getValidCode().equals(redisUtil.get(byEmailDto.getEmail()))){
            throw new CommonException(ErrorEnum.VALID_CODE_ERROR.getCode(),ErrorEnum.VALID_CODE_ERROR.getDescription());
        };
        registryToSql(byEmailDto);
    }

    @Override
    public void getVerifyCodeByEmail(String email) {
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
    }

    /**
     * 用于注册时，用户信息的数据库写入
     * @param byEmailDto
     */
    @Transactional(rollbackFor = Exception.class)
     void registryToSql(ByEmailReqDto byEmailDto){
        try {
            UserInfo userInfo = new UserInfo();
            LocalAuth localAuth = new LocalAuth();
            userInfo.setEmail(byEmailDto.getEmail());
            userInfoMapper.insertByRecord(userInfo);
            Integer userId = userInfoMapper.selectByEmail(byEmailDto.getEmail()).getId();
            localAuth.setUuid(UUid.getUUID());
            localAuth.setUserId(userId);
            localAuth.setEmail(byEmailDto.getEmail());
            localAuth.setPassword(byEmailDto.getPassword());
            localAuthMapper.insertByRecord(localAuth);
        }catch (Exception e){
            logger.info("注册信息写入数据库失败",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CommonException(ErrorEnum.REGISTRY_SQL_ERROR.getCode(),ErrorEnum.REGISTRY_SQL_ERROR.getDescription());
        }

    }
}
