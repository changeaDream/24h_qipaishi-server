package com.qipaishi.modules.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("biz_order")
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private Long storeId;
    private BigDecimal amount;
    private Integer status;
    private String payTransactionId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer deleted;
}