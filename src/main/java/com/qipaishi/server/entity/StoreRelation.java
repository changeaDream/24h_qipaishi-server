package com.qipaishi.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("store_relation")
public class StoreRelation {
    private Long id;
    private Long storeId;
    private Long branchId;
    private Integer relationType;
    private Date createTime;
    private String managerInfo;
}