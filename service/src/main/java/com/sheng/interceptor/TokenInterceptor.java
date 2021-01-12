package com.sheng.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheng.utils.JWTUtils;
import com.sheng.vo.Meta;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String method = request.getMethod();
        if(method.equals(HttpMethod.OPTIONS.name())){
            return true;
        }
        String token = request.getHeader("token");

        Meta meta = new Meta();
        try {
            JWTUtils.verify(token);
            meta.setStatus(200);
            meta.setMsg("身份验证成功");
            return true;
        } catch (TokenExpiredException e) {
            meta.setStatus(422);
            meta.setMsg("Token已经过期!!!");
        } catch (SignatureVerificationException e){
            meta.setStatus(422);
            meta.setMsg("签名错误!!!");
        } catch (AlgorithmMismatchException e){
            meta.setStatus(422);
            meta.setMsg("加密算法不匹配!!!");
        } catch (Exception e) {
            e.printStackTrace();
            meta.setStatus(422);
            meta.setMsg("无效token~~");
        }
        String res = JSON.toJSONString(meta);
        System.out.println(meta.getMsg());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(res);
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
