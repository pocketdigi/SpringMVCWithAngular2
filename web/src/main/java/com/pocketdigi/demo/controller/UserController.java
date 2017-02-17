package com.pocketdigi.demo.controller;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.biz.user.bo.UserBO;
import com.pocketdigi.demo.common.converter.BeanConverter;
import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fhp on 16/10/30.
 */
@Controller
@Slf4j
@RequestMapping(value = "user")
public class UserController{
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "",method =  RequestMethod.POST)
    @ResponseBody
    public ResultDTO<UserDTO> addUser(@RequestBody UserDTO user) {
        UserBO userBO = BeanConverter.convertObj(user,UserBO.class);

        userBiz.addUser(userBO);
        UserDTO userDTO=BeanConverter.convertObj(userBO,UserDTO.class);
        return ResultDTO.wrapSuccess(userDTO);
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<UserDTO>> list() {
        List<UserBO> userBOs = userBiz.listAll();
        return ResultDTO.wrapSuccess(BeanConverter.convertArrayList(userBOs,UserDTO.class));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO<Boolean> delete(@PathVariable Integer id) {
        return ResultDTO.wrapSuccess(userBiz.deleteUser(id));

    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<UserDTO> detail(@PathVariable Integer id) {
        UserBO user = userBiz.getUser(id);
        UserDTO userDTO=BeanConverter.convertObj(user,UserDTO.class);
        return ResultDTO.wrapSuccess(userDTO);
    }
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResultDTO<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO user) {
        UserBO user1 = userBiz.updateUser(BeanConverter.convertObj(user,UserBO.class));
        UserDTO userDTO=BeanConverter.convertObj(user1,UserDTO.class);
        return ResultDTO.wrapSuccess(userDTO);
    }
}
