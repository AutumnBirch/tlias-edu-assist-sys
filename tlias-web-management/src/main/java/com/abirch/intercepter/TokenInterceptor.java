package com.abirch.intercepter;

import com.abirch.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 获取请求路径
        String requestURI = request.getRequestURI(); // /employee/login
        // 2. 判断是否为登录请求 如果路径中包含/login，说明书登录操作，放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            return true;
        }
        // 3. 获取请求头中的token（即jwt令牌）
        String token = request.getHeader("token");

        // 4. 判断token是否存在，如果不存在，说明用户没有登录，返回错误信息（响应401状态码）
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }

        // 5. 如果token存在，校验令牌，校验失败->返回401状态码
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌为非法，响应401");
            response.setStatus(401);
            return false;
        }

        // 6. 校验通过，放行
        log.info("了令牌合法，直接放行");
        return true;
    }
}
