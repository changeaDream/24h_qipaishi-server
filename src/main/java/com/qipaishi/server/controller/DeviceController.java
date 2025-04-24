package com.qipaishi.server.controller;

import com.qipaishi.server.entity.DeviceStatusLog;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @GetMapping("/status/{deviceId}")
    public Map<String, Object> getDeviceStatus(@PathVariable String deviceId) {
        // 模拟设备状态查询
        Map<String, Object> result = new HashMap<>();
        result.put("deviceId", deviceId);
        result.put("status", 1);
        result.put("lastHeartbeat", System.currentTimeMillis());
        return result;
    }

    @PostMapping("/command")
    public Map<String, Object> sendCommand(@RequestBody Map<String, String> command) {
        // 模拟指令下发
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("commandId", "CMD_" + System.currentTimeMillis());
        return result;
    }
}
