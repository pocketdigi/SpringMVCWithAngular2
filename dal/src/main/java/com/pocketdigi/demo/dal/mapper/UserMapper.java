package com.pocketdigi.demo.dal.mapper;


import com.pocketdigi.demo.dal.po.UserPo;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.util.List;

/**
 * Created by fhp on 16/10/13.
 */
@Component
public interface UserMapper extends BaseMapper<UserPo>{
    List<UserPo> selectByName(String userName);

}
