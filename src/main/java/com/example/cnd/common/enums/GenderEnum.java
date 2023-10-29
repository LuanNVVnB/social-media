package com.example.cnd.common.enums;

import lombok.Getter;

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
}