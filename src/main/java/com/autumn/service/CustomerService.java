package com.autumn.service;

import com.autumn.model.Customer;
import com.autumn.util.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: CustomerService
 * @description: ${description}
 * @author: Created by Autumn
 * @create: 2018-10-25 09:22
 */
@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /**
     * @Description: 获取客户列表
     * @Param: []
     * @return: java.util.List<com.smart4j.chapter2.model.Customer>
     * @Author: qiuyu
     * @Date: 2018/8/13
     */
    public List<Customer> getCustomerList(String keyWord){
        List<Customer> customerList = null;
        String sql = "select * from customer";
        customerList = DBHelper.queryEntityList(Customer.class,sql);
        return customerList;
    }

    /**
     * @Description: 根据id获取客户
     * @Param: [id]
     * @return: com.smart4j.chapter2.model.Customer
     * @Author: qiuyu
     * @Date: 2018/8/13
     */
    public Customer getCustomer(long id){
        String sql = "select * from customer where id=?";
        Customer customer = DBHelper.queryEntity(Customer.class,sql,id);
        return customer;
    }
    /**
     * @Description:  创建客户
     * @Param: [fieldMap]
     * @return: boolean
     * @Author: qiuyu
     * @Date: 2018/8/13
     */
    public boolean createCustomer(Map<String,Object> fieldMap){
        Boolean b = DBHelper.insertEntity(Customer.class,fieldMap);
        return b;
    }

    /**
     * @Description: 更新客户
     * @Param: []
     * @return: boolean
     * @Author: qiuyu
     * @Date: 2018/8/13
     */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        Boolean b = DBHelper.updateEntity(Customer.class,id,fieldMap);
        return b;
    }

    /**
     * @Description: 删除客户
     * @Param: []
     * @return: boolean
     * @Author: qiuyu
     * @Date: 2018/8/13
     */
    public boolean deleteCustomer(long id){
        String sql = "delete  customer where id=?";
        Boolean b = DBHelper.deleteEntity(Customer.class,id);
        return b;
    }

}
