package me.sheepyang.qlady.http;

/**
 * Created by SheepYang on 2017/5/3.
 */

public class Api {
    //    public static final String HOST = "http://139.196.164.217:9083/GodGoddess";
    //    public static final String HOST = "http://172.21.20.2:8080/GodGoddess";
    public static final String HOST = "http://192.168.0.200:8080/GodGoddess";

    //登陆
    public static final String LOGIN = HOST + "/_check";
    //重设密码
    public static final String RESET_PASSWORD = HOST + "/tBaseUser/getPassword.action";
    //获取验证码
    public static final String GET_VERIFY_CODE = HOST + "/tBaseUser/getAcode.action";
    //获取分类
    public static final String GET_TYPE = HOST + "/tModelImglb/selectAll.action";

    public static final String IMAGE_HEAD = HOST + "/tBaseUser/selectArray.action?e.path=";
}
