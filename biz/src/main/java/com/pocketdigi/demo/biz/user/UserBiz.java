package com.pocketdigi.demo.biz.user;

import com.pocketdigi.demo.biz.user.bo.UserBo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fhp on 16/10/30.
 */
public interface UserBiz {
    UserBo addUser(UserBo userBo);
    boolean deleteUser(Integer id);
    List<UserBo> listAll();
    UserBo getUser(Integer id);
}
