package com.autumn.controller;

import com.autumn.model.Customer;
import com.autumn.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;
import java.util.Map;

/**
 * @program: CustomerController
 * @description: ${description}
 * @author: Created by Autumn
 * @create: 2018-10-25 09:35
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入客户端界面
     */
    @Action("get:/customer")
    public View index(){
        List<Customer> customerList = customerService.getCustomerList("");
        return new View("customer.jsp").addModel("customerList",customerList);
    }

    /**
     * 进入新增页面
     * @return
     */
    @Action("get:/customer_add_jsp")
    public View customer_add_jsp(){
        return new View("customer_create.jsp");
    }

    /**
     * 添加
     */
    @Action("post:/customer_add")
    public View add(Param param){
        //boolean result = customerService.createCustomer(param.getMap());  未优化前无文件的Param获取方式
        Map paramMap = param.getFieldMap();
        boolean result = customerService.createCustomer(paramMap);
        return new View("customer_create.jsp").addModel("msg","添加"+(result?"成功":"失败")+"!!!");
    }

    /**
     * 处理 创建客户请求 - 带图片
     */
    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String,Object> fieldMap = param.getFieldMap();
        FileParam fileParam = param.getFile("photo");
        boolean result = customerService.createCustomer(fieldMap,fileParam);
        return new Data(result);
    }

    /**
     * 显示客户基本信息
     */
    @Action("get:/customer_show")
    public View show(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer",customer);
    }
    /**
     * 显示客户基本信息 json格式
     */
    @Action("get:/customer_show_json")
    public Data getCostomerJsonByid(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new Data(customer);
    }
    /**
     * 删除客户信息
     * @param param
     * @return
     */
    @Action("delete:/customer_delete")
    public Data delete(Param param){
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }

    /**
     * 删除客户信息
     * @param param
     * @return
     */
    @Action("get:/customer_delete")
    public Data deleteByGET(Param param){
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}
