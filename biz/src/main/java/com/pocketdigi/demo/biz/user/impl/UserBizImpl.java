package com.pocketdigi.demo.biz.user.impl;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.biz.user.bo.UserBo;
import com.pocketdigi.demo.common.converter.BeanConverter;
import com.pocketdigi.demo.dal.mapper.UserMapper;
import com.pocketdigi.demo.dal.po.UserPo;
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
    public UserBo addUser(UserBo userBo) {
        UserPo userPo = BeanConverter.convertObj(userBo, UserPo.class);
        int insert = userMapper.insert(userPo);
        userBo.setId(userPo.getId());
        if(insert==1) {
            return userBo;
        }else{
            return null;
        }
    }

    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id)==1;
    }

    public List<UserBo> listAll() {
        List<UserPo> userPos = userMapper.selectAll();
        return BeanConverter.convertArrayList(userPos, UserBo.class);
    }
}
