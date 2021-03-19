package io.lhj.rpcfx.demo.provider;

import io.lhj.rpcfx.demo.api.User;
import io.lhj.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
