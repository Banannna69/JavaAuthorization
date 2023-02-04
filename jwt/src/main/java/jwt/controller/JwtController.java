package jwt.controller;

import jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jwt.pojo.User;
import jwt.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JwtController {
    @Autowired
    private UserService userService;



    @PostMapping("jwtLogin")
    public String login(@RequestBody User user){
        // 判断账号密码是否正确
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            // 如果正确的话就返回生成的token（注意哦，这里服务端是没有存储任何东西的）
            return JwtUtil.generate(user.getUsername());
        }
        return "登录失败，账号密码错误";
    }

    @GetMapping("JwtApi")
    public String api(HttpServletRequest request){
        userService.doSomething();
        return "success";
    }

}
