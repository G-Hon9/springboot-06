package com.example.controller;

import com.example.domain.User;
import com.example.service.UserServiceImpl;
import com.example.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl service;

    @Autowired
    EmailSender sender;

    @GetMapping("/index")
    public String index(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     *
     * @param user 用户对象，会对邮箱格式进行数据校验
     * @param result BindingResult类的实例
     * @param session HttpSession实例
     * @return
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "register";
        }

        //生成四位随机验证码
        Integer number = (int)(Math.random()*9000+1000);
        String validation = "" + number;
        String message = "您的验证码是：" + validation;

        //发送邮件
        sender.sendEmail(message, user.getEmail());
        //在session中放置验证码和user对象
        session.setAttribute("validation", validation);
        session.setAttribute("user", user);
        return "validation";
    }

    /**
     *
     * @param validation 邮箱验证码
     * @param session
     * @return
     */
    @PostMapping("/validation")
    public String success(String validation, HttpSession session){
        //如果验证码正确就注册一个新用户，否则重新输入
        if(validation.equals(session.getAttribute("validation"))){
            User user = (User)(session.getAttribute("user"));
            service.addUser(user);
            String message = "注册成功！";
            sender.sendEmail(message, user.getEmail());
            return "success";
        }
        else{
            return "redirect:/index";
        }
    }
}
