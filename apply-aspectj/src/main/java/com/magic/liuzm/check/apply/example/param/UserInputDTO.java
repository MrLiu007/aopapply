package com.magic.liuzm.check.apply.example.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/11/7 21:05
 * @description user对象
 */
@Data
public class UserInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer name;

}
