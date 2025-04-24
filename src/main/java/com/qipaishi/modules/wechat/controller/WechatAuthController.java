package com.qipaishi.modules.wechat.controller;

import com.qipaishi.common.ApiResponse;
import com.qipaishi.modules.user.entity.User;
import com.qipaishi.modules.user.service.UserService;
import com.qipaishi.modules.wechat.config.WxMaProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wechat")
public class WechatAuthController {
    private final WxMpService wxMpService;
    private final UserService userService;

    public WechatAuthController(WxMpService wxMpService, UserService userService) {
        this.wxMpService = wxMpService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse<User> login(String code, String encryptedData, String iv) {
        try {
            // 获取微信openid
            String sessionKey = wxMpService.getWxMaService().jsCode2SessionInfo(code).getSessionKey();
            // 解密用户信息
            WxMaProperties.WxMaConfig config = wxMpService.getWxMaConfig();
            String decryptData = wxMpService.getWxMaCryptUtils().decrypt(encryptedData, iv, sessionKey);
            // 解析用户数据
            User user = config.getObjectMapper().readValue(decryptData, User.class);
            // 创建或更新用户
            User existUser = userService.getByOpenId(user.getOpenId());
            if (existUser == null) {
                return ApiResponse.success(userService.createWechatUser(user.getOpenId(), user.getNickname(), user.getAvatar()));
            }
            return ApiResponse.success(existUser);
        } catch (WxErrorException | Exception e) {
            return ApiResponse.error("微信登录失败：" + e.getMessage());
        }
    }
}
