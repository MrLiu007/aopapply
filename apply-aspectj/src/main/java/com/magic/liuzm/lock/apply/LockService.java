package com.magic.liuzm.lock.apply;

import com.magic.liuzm.check.apply.example.param.UserInputDTO;
import com.magic.liuzm.lock.annotation.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author zemin.liu
 * @date 2020/5/28 13:54
 * @description 用户服务
 */
@Repository
@Slf4j
public class LockService {

    /**
     * 添加信息
     */
    @Lock(argName = "input.id" ,prefix = "liuzm" ,expire = 2 * 60)
    public boolean addUser(UserInputDTO input) {
        // 业务逻辑
        boolean result = true;

        return result;
    }


    /**
     * 删除信息
     */
    @Lock(argName = "userId" ,prefix = "liuzm" ,expire = 2 * 60)
    public boolean removeUser(String userId) {
        // 业务逻辑
        boolean result = true;

        return result;
    }


}