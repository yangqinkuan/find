/**
 * FileName: UserInfoMapper
 * Author:   yangqinkuan
 * Date:     2019-1-11 10:56
 * Description:
 */

package com.ice.find.sql.mapper.user;

import com.ice.find.sql.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    /**
     * 插入一条本地验证记录
     * @param userInfo
     * @return
     */
    int insertByRecord(UserInfo userInfo);

    /**
     * 根据邮箱获取用户信息
     * @param email
     * @return
     */
    UserInfo selectByEmail(@Param("email")String email);
}
