package com.kuzank.escluster.common.interceptor;

import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * <p>Description: </p>
 */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        // 获取请求方法上的注解，判断用户是否有权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AppAuth priorityAuth = null;

        AppAuth authAnnoOfMethod = method.getDeclaredAnnotation(AppAuth.class);
        AppAuth authAnnoOfClazz = handlerMethod.getBean().getClass().getAnnotation(AppAuth.class);

        if (authAnnoOfMethod != null) {
            priorityAuth = authAnnoOfMethod;
        } else if (authAnnoOfClazz != null) {
            priorityAuth = authAnnoOfClazz;
        } else {
            return true;
        }

        if (session == null || session.getAttribute(Constants.USER_SESSION_KEY) == null) {
            logger.info("user not logged in");
            response.sendRedirect(Constants.LOGIN_VIEW);
            return false;
        }

        logger.debug("user already logged in");
        return true;
    }

}
