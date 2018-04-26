package com.kuzank.escluster.common.interceptor;

import com.kuzank.escluster.common.bean.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: </p>
 */
@Component("globalInterceptor")
public class GlobalInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {

            modelAndView.setViewName(Constants.NOTFOUND_VIEW);

        } else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {

            modelAndView.setViewName(Constants.SERVERERROR_VIEW);
        }
    }
}
