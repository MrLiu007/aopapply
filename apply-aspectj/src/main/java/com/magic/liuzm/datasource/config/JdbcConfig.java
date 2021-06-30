package com.magic.liuzm.datasource.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zemin.liu
 * @date 2021/6/29 16:22
 * @description Jdbc公共配置
 */
@Data
public class JdbcConfig {

    /**
     * jdbc url
     */
    private String url;

    /**
     * jdbc username
     */
    private String username;

    /**
     * jdbc password
     */
    private String password;

    /**
     * 检查属性
     */
    public boolean checking(){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
}
