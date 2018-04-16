package com.kuzank.escluster.common.interceptor;

import com.kuzank.escluster.common.bean.AppAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author kuzan
 * @since 2018/01/28
 */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 1 验证登录
//        HttpSession session = request.getSession();
//        if (session == null || session.getAttribute(Constants.USER_SESSION_KEY) == null) {
//
//            logger.info("user not logged in");
//            response.sendRedirect(Constants.LOGIN_VIEW);
//            return false;
//        }
//        logger.debug("user already logged in");

        // Authorization test:
        //Credential credential = (Credential) session.getAttribute(Constants.USER_SESSION_KEY);


        // 2 获取请求方法上的注解，判断用户是否有权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Annotation[] annotations = method.getAnnotations();

        AppAuth appAuthAnnotation = method.getDeclaredAnnotation(AppAuth.class);
        if (appAuthAnnotation == null) {
            
        }


        return true;
    }

}
