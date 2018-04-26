package com.kuzank.escluster.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 */
@Aspect   //定义一个切面
@Configuration
public class ControllerAOP {

//    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
//
//    // 定义切点Pointcut
//    @Pointcut("execution(public com.kuzank.escluster.common.bean.ResultBean *(..))")
//    public void excudeService() {
//    }
//
//    @Around("excudeService()")
//    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
//        long startTime = System.currentTimeMillis();
//
//        ResultBean<?> result;
//        try {
//            result = (ResultBean<?>) pjp.proceed();
//            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
//        } catch (Throwable e) {
//            result = handlerException(pjp, e);
//        }
//
//        return result;
//    }
//
//    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
//        ResultBean<?> result = new ResultBean();
//        // 已知异常
//        if (e instanceof CheckException) {
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        } else if (e instanceof UnloginException) {
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        } else {
//            logger.error(pjp.getSignature() + " error ", e);
//            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
//            result.setMsg(e.toString());
//            result.setCode(ResultBean.FAIL);
//        }
//        return result;
//    }

//    // 定义切点Pointcut
//    @Pointcut("execution(* com.jiaobuchong.web.*Controller.*(..))")
//    public void excudeService() {
//    }
//
//    @Around("excudeService()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
//        logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
//
//        // result的值就是被拦截方法的返回值
//        Object result = pjp.proceed();
//        Gson gson = new Gson();
//        logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
//        return result;
//    }

}


//<!-- aop -->
//<aop:aspectj-autoproxy />
//<beans:entity id="controllerAop" class="plm.common.aop.ControllerAOP" />
//
//<aop:config>
//<aop:aspect id="myAop" ref="controllerAop">
//    <aop:pointcut id="target" expression="execution(public plm.common.beans.ResultBean *(..))" />
//    <aop:around method="handlerControllerMethod" pointcut-ref="target" />
//</aop:aspect>
//</aop:config>
