package com.qipaishi.modules.device.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("iot_device")
public class Device {
    @TableId(type = IdType.ASSIGN_ID)
    private String deviceId;
    private String deviceName;
    private Integer deviceType;
    private Integer status;
    @TableField("store_id")
    private Long storeId;
    @TableField("tenant_id")
    private String tenantId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}