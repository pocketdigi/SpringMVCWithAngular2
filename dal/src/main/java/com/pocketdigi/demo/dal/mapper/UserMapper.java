package com.pocketdigi.demo.dal.mapper;


import com.pocketdigi.demo.dal.po.UserPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fhp on 16/10/13.
 */
@Component
public interface UserMapper extends BaseMapper<UserPO>{
    List<UserPO> selectByName(String userName);

}
