package com.qipaishi.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qipaishi.modules.user.entity.User;
import com.qipaishi.modules.user.mapper.UserMapper;
import com.qipaishi.modules.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByOpenId(String openId) {
        return lambdaQuery().eq(User::getOpenId, openId).one();
    }

    @Override
    public User createWechatUser(String openId, String nickname, String avatar) {
        User user = new User();
        user.setOpenId(openId);
        user.setNickname(nickname);
        user.setAvatar(avatar);
        save(user);
        return user;
    }
}
