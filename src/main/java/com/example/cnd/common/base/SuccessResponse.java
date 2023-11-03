package com.example.cnd.common.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: com.example.cnd.common.base
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Success Response </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessResponse {

    private String message = "success";
    private Object response;

    public SuccessResponse(Object response) {
        this.response = response;
    }
}
