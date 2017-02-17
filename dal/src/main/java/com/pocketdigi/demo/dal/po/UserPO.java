package com.pocketdigi.demo.dal.po;

import lombok.Data;

@Data
public class UserPO extends BasePO {
    private String userName;
    private int userAge;
    private String avatar;
}
