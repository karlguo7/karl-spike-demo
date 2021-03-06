package cn.karlguo.spike.service;

import cn.karlguo.spike.pojo.User;
import cn.karlguo.spike.vo.LoginVo;
import cn.karlguo.spike.vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author karlguo
 * @since 2021-05-12
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
