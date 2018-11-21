package com.autumn.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.aop.Aspect;
import org.smart4j.framework.aop.AspectProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 拦截所有Controller方法
 */
@Aspect(Controller.class)
public class ControllerResultAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.smart4j.framework.aop.ControllerAspect.class);
    private long begin;    //方法开始时间

    /**
     * 前置增强
     * @param cls    目标类
     * @param method 目标方法
     * @param params 目标方法参数
     */
    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("ControllerResultAspect---------begin---------");
        LOGGER.debug("请求时间:"+new SimpleDateFormat("yyyy-MM-ss HH;mm:ss").format(new Date()));
        LOGGER.debug(String.format("类: %s",cls.getName()));
        LOGGER.debug(String.format("方法: %s",method.getName()));
        LOGGER.debug(String.format("参数: %s", Arrays.toString(params)));
        begin = System.currentTimeMillis();
    }

    /**
     * 后置增强
     * @param cls    目标类
     * @param method 目标方法
     * @param result 目标方法返回结果
     */
    @Override
    public void after(Class<?> cls, Method method, Object result) throws Throwable {
        LOGGER.debug(String.format("结果: %s", result.toString()));
        LOGGER.debug("ControllerResultAspect---------end---------");
    }
}
