package com.pocketdigi.demo.user;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.biz.user.bo.UserBO;
import com.pocketdigi.demo.common.converter.BeanConverter;
import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Exception on 2017/3/17.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserBiz userBiz;

    public ResultDTO<UserDTO> addUser(UserDTO user) {
        UserBO userBO = userBiz.addUser(BeanConverter.convertObj(user, UserBO.class));
        return ResultDTO.wrapSuccess(BeanConverter.convertObj(userBO,UserDTO.class));
    }

    public ResultDTO<Boolean> deleteUser(Integer id) {
        return ResultDTO.wrapSuccess(userBiz.deleteUser(id));
    }

    public ResultDTO<List<UserDTO>> listAll() {
        List<UserBO> userBOS = userBiz.listAll();
        List<UserDTO> userDTOS = BeanConverter.convertArrayList(userBOS, UserDTO.class);
        return ResultDTO.wrapSuccess(userDTOS);
    }
}
