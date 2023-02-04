package session.service.impl;

import org.springframework.stereotype.Service;
import session.pojo.User;
import session.service.UserService;
import session.utils.RequestContext;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        User user = RequestContext.getCurrentUser();
        System.out.println("service层---当前登录用户对象：" + user);
    }
}
