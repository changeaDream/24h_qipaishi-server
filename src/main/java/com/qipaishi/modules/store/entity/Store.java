package com.qipaishi.modules.store.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("biz_store")
public class Store {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String contact;
    private String businessHours;
    @TableField("tenant_id")
    private String tenantId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableLogic
    private Integer deleted;
}