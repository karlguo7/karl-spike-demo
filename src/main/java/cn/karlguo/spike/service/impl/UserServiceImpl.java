package cn.karlguo.spike.service.impl;

import cn.karlguo.spike.exception.GlobalException;
import cn.karlguo.spike.mapper.UserMapper;
import cn.karlguo.spike.pojo.User;
import cn.karlguo.spike.service.IUserService;
import cn.karlguo.spike.util.CookieUtil;
import cn.karlguo.spike.util.MD5Util;
import cn.karlguo.spike.util.UUIDUtil;
import cn.karlguo.spike.vo.LoginVo;
import cn.karlguo.spike.vo.RespBean;
import cn.karlguo.spike.vo.RespBeanEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author karlguo
 * @since 2021-05-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断用户名或密码是否为空，如果为空返回异常
        /*if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }*/
        //校验手机号格式是否正确
        /*if (!ValidatorUtil.isMobile(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }*/
        //校验通过，通过mobile在数据库中查找用户
        User user = userMapper.selectById(mobile);
        if (Objects.isNull(user)) {//如果查询结果为空，返回错误
            //return RespBean.error(RespBeanEnum.USER_NOT_FOUND_ERROR);
            throw new GlobalException(RespBeanEnum.USER_NOT_FOUND_ERROR);
        }
        //查询到用户，校验密码
        if (!MD5Util.fromPassToDBPass(password, user.getSlat()).equals(user.getPassword())) {
            //return RespBean.error(RespBeanEnum.PASSWORD_ERROR);
            throw new GlobalException(RespBeanEnum.PASSWORD_ERROR);
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();
        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);

        return RespBean.success();
    }
}
