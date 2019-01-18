/**
 * FileName: EmailRegistryDto
 * Author:   yangqinkuan
 * Date:     2019-1-13 17:55
 * Description: 邮箱注册的内部传输DTO
 */

package com.ice.find.registry.dto.email;

public class EmailRegistryDto {
    private String email;
    private String password;
    private String validCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
