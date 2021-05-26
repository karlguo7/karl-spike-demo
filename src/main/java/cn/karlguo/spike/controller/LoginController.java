package cn.karlguo.spike.controller;

import cn.karlguo.spike.service.IUserService;
import cn.karlguo.spike.vo.LoginVo;
import cn.karlguo.spike.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/*
 * @Classname LoginController
 * @Date 2021/5/12
 * @author karlguo
 * @Description
 */
@Controller
@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;

    /**
     * []
     *
     * @return java.lang.String
     * @Description to login page
     */
    //@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /*
     *
     * @return cn.karlguo.spike.vo.RespBean
     * @Description 登录功能
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doLogin(@Valid @RequestBody LoginVo loginVo) {
        return userService.doLogin(loginVo);
    }


}
