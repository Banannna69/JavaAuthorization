package jwt.service.impl;

import jwt.service.UserService;
import jwt.utils.UserContext;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        String currentUserName = UserContext.getCurrentUserName();
        System.out.println("Service层---当前用户登录名：" + currentUserName);
    }
}
