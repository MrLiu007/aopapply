package com.magic.liuzm.cache.apply.example;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/11/7 21:05
 * @description user对象
 */
@Data
public class UserOutputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer name;

}
