package com.abirch.controller;

import com.abirch.pojo.OperateLogQueryParam;
import com.abirch.pojo.OperateLog;
import com.abirch.pojo.PageResult;
import com.abirch.pojo.Result;
import com.abirch.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private ReportService reportService;
    /**
     * 分页查询操作日志列表
     * @param operateLogQueryParam
     * @return
     */
    @GetMapping("/page")
    public Result getLogList(OperateLogQueryParam operateLogQueryParam){
        log.info("日志列表查询:{}", operateLogQueryParam);
        PageResult<OperateLog> pageResult = reportService.page(operateLogQueryParam);
        return Result.success(pageResult);
    }
}
