package com.abirch.controller;

import com.abirch.pojo.Emp;
import com.abirch.pojo.LoginInfo;
import com.abirch.pojo.Result;
import com.abirch.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录请求处理Controller
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    /**
     * 登录方法
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录：{}",emp);
        LoginInfo info = empService.login(emp);
        if (info!=null) {
            return Result.success(info);
        }else {
            return Result.error("用户名或密码错误");
        }
    }
}
