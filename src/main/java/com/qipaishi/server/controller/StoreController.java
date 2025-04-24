package com.qipaishi.server.controller;

import com.qipaishi.server.entity.StoreRelation;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    @PostMapping("/maintain")
    public Map<String, Object> maintainStoreInfo(@RequestBody Map<String, Object> storeInfo) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "门店信息更新成功");
        result.put("storeId", storeInfo.get("storeId"));
        return result;
    }

    @PostMapping("/branch")
    public Map<String, Object> associateBranch(@RequestBody StoreRelation relation) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("relationId", System.currentTimeMillis());
        return result;
    }

    @PostMapping("/business-hours")
    public Map<String, Object> configureBusinessHours(@RequestBody Map<String, String> hours) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("openingTime", hours.get("openingTime"));
        result.put("closingTime", hours.get("closingTime"));
        return result;
    }
}
