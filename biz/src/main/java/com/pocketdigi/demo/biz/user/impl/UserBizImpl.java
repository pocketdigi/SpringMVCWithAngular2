package com.pocketdigi.demo.biz.user.impl;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.biz.user.bo.UserBO;
import com.pocketdigi.demo.common.converter.BeanConverter;
import com.pocketdigi.demo.dal.mapper.UserMapper;
import com.pocketdigi.demo.dal.po.UserPO;
import com.pocketdigi.demo.user.UserService;
import com.pocketdigi.demo.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fhp on 16/10/30.
 */
@Service
public class UserBizImpl implements UserBiz{
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    public UserBO addUser(UserBO userBO) {
        UserPO userPO = BeanConverter.convertObj(userBO, UserPO.class);
        int insert = userMapper.insert(userPO);
        userBO.setId(userPO.getId());
        if(insert==1) {
            return userBO;
        }else{
            return null;
        }
    }

    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id)==1;
    }

    public List<UserBO> listAll() {
        List<UserPO> userPOs = userMapper.selectAll();
        return BeanConverter.convertArrayList(userPOs, UserBO.class);
    }

    public UserBO getUser(Integer id) {
        UserPO userPO = userMapper.selectById(id);
        return BeanConverter.convertObj(userPO,UserBO.class);

    }

    public UserBO updateUser(UserBO userBO) {
        userMapper.updateById(BeanConverter.convertObj(userBO, UserPO.class));
        return getUser(userBO.getId());
    }

    public List<UserBO> listAllUseDubbo() {
        List<UserDTO> data = userService.listAll().getData();
        return BeanConverter.convertArrayList(data,UserBO.class);
    }


}
