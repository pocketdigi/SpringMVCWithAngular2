package com.pocketdigi.demo.biz.user.bo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库基础字段
 * Created by fhp on 16/10/13.
 */
@Data
public class BaseBO implements Serializable{
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;

}
