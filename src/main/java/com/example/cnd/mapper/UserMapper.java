package com.example.cnd.mapper;

import com.example.cnd.dao.entity.User;
import com.example.cnd.response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Mapper User </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/10/2024</span>
 **/
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UsersResponse userToUserResponse(User user);

    User userDTOToUser(UsersResponse userDTO);
}