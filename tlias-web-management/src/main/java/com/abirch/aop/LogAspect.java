package com.abirch.aop;

import com.abirch.anno.Log;
import com.abirch.mapper.OperateLogMapper;
import com.abirch.pojo.OperateLog;
import com.abirch.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(logAnnotation)")
    public Object recordLog(ProceedingJoinPoint joinPoint, Log logAnnotation) throws Throwable {
        // 记录开始时间
        long beginTime = System.currentTimeMillis();

        // 创建操作日志对象
        OperateLog operateLog = new OperateLog();

        try {
            // 操作人ID（从JWT令牌中解析）
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("token");
                
                if (token != null && !token.isEmpty()) {
                    try {
                        Claims claims = JwtUtils.parseJWT(token);
                        operateLog.setOperateEmpId((Integer) claims.get("id"));
                    } catch (Exception e) {
                        log.error("解析JWT令牌失败", e);
                    }
                }
            }

            // 操作时间
            operateLog.setOperateTime(LocalDateTime.now());

            // 目标类的全类名
            String className = joinPoint.getTarget().getClass().getName();
            operateLog.setClassName(className);

            // 目标方法的方法名
            String methodName = joinPoint.getSignature().getName();
            operateLog.setMethodName(methodName);

            // 方法运行时参数
            String methodParams = Arrays.toString(joinPoint.getArgs());
            operateLog.setMethodParams(methodParams);

            // 执行目标方法
            Object result = joinPoint.proceed();

            // 返回值
            operateLog.setReturnValue(result != null ? result.toString() : "void");

            return result;
        } finally {
            // 方法执行时长
            long endTime = System.currentTimeMillis();
            operateLog.setCostTime(endTime - beginTime);

            log.info("记录日志：{}",operateLog);
            // 保存操作日志到数据库
            operateLogMapper.insert(operateLog);
        }
    }
}