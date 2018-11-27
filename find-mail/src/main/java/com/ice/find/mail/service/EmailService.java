/**
 * FileName: EmailService
 * Author:   yangqinkuan
 * Date:     2018-11-27 16:47
 * Description: interface of emain sender
 */

package com.ice.find.mail.service;

import java.util.Map;

public interface EmailService {
    /*
     * @Author yangqinkuan
     * @Description 发送简单邮件
     * @Param to 收件方邮箱地址
     * @Param subject 邮件主题
     * @Param content 邮件内容
     * @return
     **/
    public void sendSimpleEmail(String to, String subject, String content);
    /*
     * @Author yangqinkuan
     * @Description 发送html格式邮件
     * @Param to 收件方邮箱地址
     * @Param subject 邮件主题
     * @Param content 邮件内容
     * @return
     **/
    public void sendHtmlEmail(String to, String subject, String content);
    /*
     * @Author yangqinkuan
     * @Description 发送带附件的邮件
     * @Param to 收件方邮箱地址
     * @Param subject 邮件主题
     * @Param content 邮件内容
     * @return
     **/
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath);
    /*
     * @Author yangqinkuan
     * @Description 发送带静态资源的邮件
     * @Param to 收件方邮箱地址
     * @Param subject 邮件主题
     * @Param content 邮件内容
     * @return
     **/
    public void sendInlineResourceEmail(String to, String subject, String content, String rscPath, String rscId);


    public void sendTemplateMail(String to, String subject, Map<String,String> map);
}
