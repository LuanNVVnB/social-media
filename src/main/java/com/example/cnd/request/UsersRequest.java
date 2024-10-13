package com.example.cnd.request;

import com.example.cnd.common.base.PagingBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

/**
 * File: com.example.cnd.request
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UsersRequest </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">10/12/2024</span>
 **/
@Data
@Builder
@NoArgsConstructor
public class UsersRequest extends PagingBase {
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    private LocalDate birthday;

    @ConstructorProperties({"user_name", "first_name", "last_name", "address", "birthday"})
    UsersRequest(String userName, String firstName, String lastName, String address, LocalDate birthday) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
    }
}
