package com.pocketdigi.demo.user;

import com.pocketdigi.demo.dto.ResultDTO;
import com.pocketdigi.demo.user.dto.UserDTO;

import java.util.List;

/**
 * Created by fhp on 16/10/30.
 */
public interface UserService {
    ResultDTO<UserDTO> addUser(UserDTO user);
    ResultDTO<Boolean> deleteUser(Integer id);
    ResultDTO<List<UserDTO>> listAll();
}
