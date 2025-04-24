package com.qipaishi.modules.user.service;

import com.qipaishi.modules.user.entity.User;

public interface UserService {
    User getByOpenId(String openId);
    User createWechatUser(String openId, String nickname, String avatar);
    Integer updateBalance(Long userId, Integer amount);
}