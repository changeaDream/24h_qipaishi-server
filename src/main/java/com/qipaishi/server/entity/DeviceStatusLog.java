package com.qipaishi.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("device_status_log")
public class DeviceStatusLog {
    private Long id;
    private String deviceId;
    private Integer status;
    private Date createTime;
    private String extraInfo;
}