package cn.karlguo.spike.exception;

import cn.karlguo.spike.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Classname GlobalException
 * @Date 2021/5/26
 * @author karlguo
 * @Description 全局异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
