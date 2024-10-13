package com.example.cnd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: com.example.cnd.response
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UsersResponse </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/10/2024</span>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    private String address;
    private String birthday;
}
