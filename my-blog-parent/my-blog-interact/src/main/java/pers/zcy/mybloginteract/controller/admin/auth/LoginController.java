package pers.zcy.mybloginteract.controller.admin.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.zcy.myblogboot.entity.User;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    DefaultWebSecurityManager mySecurityManager;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        SecurityUtils.setSecurityManager(mySecurityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        User principal = (User)subject.getPrincipal();
        if (null != principal) {
            session.setAttribute("user",principal);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
