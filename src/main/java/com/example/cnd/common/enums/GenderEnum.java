package com.example.cnd.common.enums;

import lombok.Getter;

/**
 * File: com.example.cnd.common.enums
 * Description:  <div style="font-weight: bold; color: #0073e6;"> GenderEnum </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
 **/
@Getter
public enum GenderEnum {
    Male(1, "male"),
    Female(0, "female"),
    Other(2, "other");

    private int genderId;
    private String genderContent;

    GenderEnum(int genderId, String genderContent) {
        this.genderId = genderId;
        this.genderContent = genderContent;
    }

    public static boolean contains(Object checkGenderContent) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (checkGenderContent.equals(gender.genderId)) {
                return true;
            }
        }
        return false;
    }

}