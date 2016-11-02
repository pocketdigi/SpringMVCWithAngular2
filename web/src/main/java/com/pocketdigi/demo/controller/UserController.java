package com.pocketdigi.demo.controller;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.biz.user.bo.UserBo;
import com.pocketdigi.demo.common.converter.BeanConverter;
import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.UserClient;
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
public class UserController implements UserClient {
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "",method =  RequestMethod.POST)
    @ResponseBody
    public ResultDTO<UserDTO> addUser(@RequestBody UserDTO user) {
        UserBo userBo = BeanConverter.convertObj(user,UserBo.class);

        userBiz.addUser(userBo);
        UserDTO userDTO=BeanConverter.convertObj(userBo,UserDTO.class);
        return ResultDTO.wrapSuccess(userDTO);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO<String> deleteUser(Integer id) {
        userBiz.deleteUser(id);
        return ResultDTO.wrapSuccess("");
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<UserDTO>> list() {
        List<UserBo> userBos = userBiz.listAll();
        return ResultDTO.wrapSuccess(BeanConverter.convertArrayList(userBos,UserDTO.class));
    }
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO<String> delete(@PathVariable Integer id) {
        boolean deleteResult = userBiz.deleteUser(id);
        if(deleteResult) {
            return ResultDTO.wrapSuccess("成功");
        }else{
            return ResultDTO.wrapError(-1,"失败");
        }

    }
}
