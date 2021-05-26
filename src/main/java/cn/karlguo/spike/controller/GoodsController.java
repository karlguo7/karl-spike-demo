package cn.karlguo.spike.controller;

import cn.karlguo.spike.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/*
 * @Classname GoodsController
 * @Date 2021/5/26
 * @author karlguo
 * @Description
 */
@Controller
@RequestMapping(value = "/goods", method = {RequestMethod.GET, RequestMethod.POST})
public class GoodsController {

    /**
     * [session, model, ticket]
     *
     * @return java.lang.String
     * @Description 跳转到商品列表页
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}
