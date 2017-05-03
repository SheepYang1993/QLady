package me.sheepyang.qlady.http;

/**
 * Created by SheepYang on 2017/2/20 21:41.
 */

public class Api {

    public static final String BASE_HOST = "http://116.62.100.238/keaixue-api/";
//    public static final String BASE_HOST = "http://192.168.16.113:8080/api/";

    /**
     * 登陆
     */
    public static final String LOGIN = BASE_HOST + "api/user/logon.json";
    /**
     * 通讯录列表
     */
    public static final String CUSTOMER_LIST = BASE_HOST + "api/customer/selectCustomerList.json";
    /**
     * 报班记录列表
     */
    public static final String CUSTOMER_CLASS_LIST = BASE_HOST + "api/customer/selectCustomerClassList.json";
    /**
     * 家长回访记录列表
     */
    public static final String CUSTOMER_VISIT_LIST = BASE_HOST + "api/customer/selectCustomerVisitList.json";
    /**
     * 创建家长回访记录
     */
    public static final String SAVE_VISIT = BASE_HOST + "api/customer/save.json";
    /**
     * 签到学生列表
     */
    public static final String SELECT_CUSTOMER_BY_STORE = BASE_HOST + "api/customer/selectCustomerByStore.json";

    /**
     * 保存签到信息
     */
    public static final String SAVE_SIGN = BASE_HOST + "api/customer/saveSign.json";
    /**
     * 课程类目
     */
    public static final String SELECT_PROJECT = BASE_HOST + "api/customer/selectProject.json";
    /**
     * 签到记录
     */
    public static final String SELECT_CUSTOMER_SIGN = BASE_HOST + "api/customer/selectCustomerSign.json";
}
