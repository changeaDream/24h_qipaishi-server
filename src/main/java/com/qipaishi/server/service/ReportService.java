package com.qipaishi.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qipaishi.server.entity.DeviceStatusLog;
import com.qipaishi.server.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportService extends ServiceImpl<OrderMapper, Order> {

    // 订单统计（按时间维度）
    public Map<String, Object> getOrderStats(String timeType) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.select("DATE_FORMAT(create_time,'%Y-%m-%d') as date", 
                     "SUM(amount) as total_amount", 
                     "COUNT(*) as order_count")
              .groupBy("date")
              .orderByAsc("date");

        if("week".equals(timeType)) {
            wrapper.apply("create_time >= {0}", LocalDateTime.now().minusWeeks(1));
        } else if("month".equals(timeType)) {
            wrapper.apply("create_time >= {0}", LocalDateTime.now().minusMonths(1));
        }

        return executeQuery(wrapper);
    }

    // 设备使用率分析
    public Map<String, Object> analyzeDeviceUsage(String deviceId) {
        QueryWrapper<DeviceStatusLog> wrapper = new QueryWrapper<>();
        wrapper.select("status", "COUNT(*) as count")
              .eq("device_id", deviceId)
              .groupBy("status");

        List<Map<String, Object>> logs = deviceStatusLogMapper.selectMaps(wrapper);
        Map<String, Object> result = new HashMap<>();
        logs.forEach(log -> {
            result.put(log.get("status").toString(), log.get("count"));
        });
        return result;
    }

    private Map<String, Object> executeQuery(QueryWrapper<?> wrapper) {
        List<Map<String, Object>> data = baseMapper.selectMaps(wrapper);
        Map<String, Object> result = new LinkedHashMap<>();
        data.forEach(row -> {
            String date = row.get("date").toString();
            result.put(date, new HashMap<>() {{
                put("total_amount", row.get("total_amount"));
                put("order_count", row.get("order_count"));
            }});
        });
        return result;
    }
}