package com.autumn.aop;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.aop.Aspect;
import org.smart4j.framework.aop.AspectProxy;
import org.smart4j.framework.util.DBHelper;

import java.lang.reflect.Method;

/**
 * 拦截器方式实现切面
 * 已经在框架中用Proxy接口实现了
 * 这里不重复实现
 */
//@Aspect(Service.class)
public class TransactionAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionAspect.class);

    /**
     * 前置增强
     * @param cls    目标类
     * @param method 目标方法
     * @param params 目标方法参数
     */
    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("TransactionAspect---------begin---------");
        LOGGER.debug(String.format("class: %s",cls.getName()));
        LOGGER.debug(String.format("method: %s",method.getName()));
        try {
            DBHelper.beginTransaction();   //开启事务
            LOGGER.debug("TransactionAspect - begin transaction");
        }catch (Exception e){
            DBHelper.rollbackTransaction();   //异常回滚
            LOGGER.debug("TransactionAspect - rollback transaction");
            throw  e;
        }finally {
        }

    }

    /**
     * 后置增强
     * @param cls    目标类
     * @param method 目标方法
     * @param result 目标方法返回结果
     */
    @Override
    public void after(Class<?> cls, Method method, Object result) throws Throwable {
        try {
            DBHelper.commitTransaction();   //提交方法
            LOGGER.debug("TransactionAspect - commit transaction");
        }catch (Exception e){
            DBHelper.rollbackTransaction();  //异常回滚
            LOGGER.debug("TransactionAspect - rollback transaction");
            throw  e;
        }finally {
        }
    }

    /**
     * 拦截只有Transaction注解的方法
     * @param cls    目标类
     * @param method 目标方法
     * @param params 目标方法参数
     * @return 返回是否拦截
     */
    @Override
    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        if (method.isAnnotationPresent(Transaction.class)){
            return true;
        }
        return false;
    }
}
