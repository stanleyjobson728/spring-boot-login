package com.lxyspace.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

// https://segmentfault.com/a/1190000014211773


@Controller
public class IndexController {

    @RequestMapping({"/", "/login.html"})
    public String index() {
        return "/login";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        return "login";
    }


    @PostMapping("/user/login")
    public String logininmain(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Map<String, Object> map,
                              //设置变量session
                              HttpSession session) {
        if ("admin".equals(username) && "123456".equals(password)) {
            //设置账号为：admin，密码为：123456
            //保存username的值设置名为user
            session.setAttribute("user",username);
            return "redirect:/main.html";
        } else {
            //错误时设置msg
            map.put("msg","账号或者密码出错");
            //账号或者密码错误，返回主页面
            return "login";
        }
    }

    @PostMapping("/user/out")
    public String loginOut(HttpSession session) {
        session.setAttribute("user","");
        return "login";
    }
}
