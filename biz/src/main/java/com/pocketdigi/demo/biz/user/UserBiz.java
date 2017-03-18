package com.pocketdigi.demo.biz.user;

import com.pocketdigi.demo.biz.user.bo.UserBO;

import java.util.List;

/**
 * Created by fhp on 16/10/30.
 */
public interface UserBiz {
    UserBO addUser(UserBO userBO);
    boolean deleteUser(Integer id);
    List<UserBO> listAll();
    UserBO getUser(Integer id);
    UserBO updateUser(UserBO userBO);
    List<UserBO> listAllUseDubbo();
}
