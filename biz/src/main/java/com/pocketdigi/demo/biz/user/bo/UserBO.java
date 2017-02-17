package com.pocketdigi.demo.biz.user.bo;
import lombok.Data;

@Data
public class UserBO extends BaseBO {
    private String userName;
    private int userAge;
    private String avatar;
}
