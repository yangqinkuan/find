/**
 * FileName: LocalAuthMapper
 * Author:   yangqinkuan
 * Date:     2019-1-11 10:10
 * Description:
 */

package com.ice.find.sql.mapper.user;

import com.ice.find.sql.entity.user.LocalAuth;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface LocalAuthMapper {
    /**
     * 插入一条本地验证记录
     * @param localAuth
     * @return
     */
    int insertByRecord(LocalAuth localAuth);

    /**
     * 根据email 查询本地验证记录
     * @param email
     * @return
     */
    LocalAuth selectByEmail(@Param("email") String email);
}
