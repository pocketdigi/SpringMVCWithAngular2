package com.pocketdigi.demo.user.dto;

import com.pocketdigi.demo.dto.BaseDTO;
import lombok.Data;

/**
 * Created by fhp on 16/10/30.
 */
@Data
public class UserDTO extends BaseDTO {
    String userName;
    int userAge;
    String avatar;
}
