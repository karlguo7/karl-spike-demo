package cn.karlguo.spike.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * @Classname RespBeanEnum
 * @Date 2021/5/12
 * @author karlguo
 * @Description
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    USER_NOT_FOUND_ERROR(500110, "用户不存在"),
    //Bind error
    BIND_ERROR(500120,"参数校验异常"),
    //登录模块
    LOGIN_ERROR(500210, "用户名或密码错误"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    PASSWORD_ERROR(500212, "密码错误");


    private final Integer code;
    private final String message;
}
