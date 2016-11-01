package com.pocketdigi.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库基础字段
 * Created by fhp on 16/10/13.
 */
@Data
public class BaseDTO implements Serializable{
    Integer id;
    Date gmtCreate;
    Date gmtModified;


}
