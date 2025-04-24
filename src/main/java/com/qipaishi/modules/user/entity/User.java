package com.qipaishi.modules.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String nickname;
    private String mobile;
    private String avatar;
    @TableField("open_id")
    private String openId;
    @TableField("tenant_id")
    private String tenantId;
    private Integer balance;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}