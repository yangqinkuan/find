/**
 * FileName: Registry
 * Author:   yangqinkuan
 * Date:     2019-1-11 11:37
 * Description: 用户注册接口
 */

package com.ice.find.registry.service;

import com.ice.find.registry.dto.email.ByEmailReqDto;

public interface Registry {

    Boolean isExistedEmail(String email);

    void registryByMail(ByEmailReqDto byEmailDto);

    void getVerifyCodeByEmail(String email);


}
