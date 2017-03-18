package com.pocketdigi.demo.controller;

import com.pocketdigi.demo.biz.user.UserBiz;
import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fhp on 16/10/30.
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    UserBiz userBiz;
    @RequestMapping(value = "/test",method =  RequestMethod.GET)
    @ResponseBody
    public ResultDTO<UserDTO> index(Model model) {

        log.error("common-lang3能否正常用{}", StringUtils.isBlank("   "));
        model.addAttribute("describe","TEST");
        UserDTO userDTO=new UserDTO();
        userDTO.setUserName("23fas");
        return ResultDTO.wrapSuccess(userDTO);
    }

    @RequestMapping(value = "")
    public String freemarker(Model model) {
        model.addAttribute("describe","adsfasfasdf");
        return "index";
    }
    @RequestMapping(value = "dubbo")
    @ResponseBody
    public ResultDTO dubboTest() {
        return ResultDTO.wrapSuccess(userBiz.listAllUseDubbo());
    }
}
