/**
 * FileName: RegistryImpl
 * Author:   yangqinkuan
 * Date:     2019-1-11 11:43
 * Description:
 */

package com.ice.find.registry.service;


import com.ice.find.registry.dto.email.ByEmailReqDto;
import com.ice.find.sql.entity.user.LocalAuth;
import com.ice.find.sql.mapper.user.LocalAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistryImpl implements Registry{

    @Autowired
    private LocalAuthMapper localAuthMapper;

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
}
