package session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import session.pojo.User;
import session.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(@RequestBody User user, HttpSession session){
        if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
            session.setAttribute("user",user);
            return "登录成功";
        }
        return "登录失败，账号或密码错误";
    }


    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "退出成功";
    }

    @GetMapping("api")
    public String api(HttpSession session){
        //模拟api，访问之前检查登录
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "请先登录";
        }

        return "success";
    }

    @GetMapping("doSomething")
    public String api() {
        // 各种业务操作
        userService.doSomething();
        return "doSomething success";
    }
}
