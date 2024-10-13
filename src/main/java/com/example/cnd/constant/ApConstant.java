package com.example.cnd.constant;

/**
 * File: com.example.cnd.constant
 * Description:  <div style="font-weight: bold; color: #0073e6;"> ApConstant </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
public class ApConstant {
    public static String versionUrl = "/api/v1";

    public static class EndPoint {

        public static final String HELLO_DB = versionUrl + "/hello";
        public static final String AUTH_URL = versionUrl + "/auths/**";
        public static final String AUTH_LOGOUT = AUTH_URL + "/logout";
        public static final String USER_URL = versionUrl + "/users/**";
    }

    public static class BASE_API {
        public static final String AUTH = "/api/v1/auths";
        public static final String USER = "/api/v1/users";
    }
}
