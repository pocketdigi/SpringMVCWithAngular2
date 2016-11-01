package com.pocketdigi.demo.user;

import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.dto.UserDTO;

/**
 * Created by fhp on 16/10/30.
 */
public interface UserClient {
    ResultDTO<UserDTO> addUser(UserDTO user);
    ResultDTO<String> deleteUser(Integer id);
}
