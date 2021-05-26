package cn.karlguo.spike.exception;

import cn.karlguo.spike.vo.RespBean;
import cn.karlguo.spike.vo.RespBeanEnum;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * @Classname GlobalExceptionHandler
 * @Date 2021/5/26
 * @author karlguo
 * @Description 异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e) {
        //如果异常是GlobalException异常，return GlobalException的异常
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        } /*else if (e instanceof BindException) {//如果异常是校验异常(BindException)，return具体的异常信息
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage(RespBeanEnum.BIND_ERROR.getMessage() +":"+ ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }*/ else if (e instanceof MethodArgumentNotValidException) {//如果异常是校验异常(MethodArgumentNotValidException)，return具体的异常信息
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage(RespBeanEnum.BIND_ERROR.getMessage() + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        //如果都不是，就return服务器异常
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
