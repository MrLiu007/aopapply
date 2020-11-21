package com.magic.liuzm.check.apply.example.ultravires;

import com.magic.liuzm.check.annotation.BizCheckUltravires;
import com.magic.liuzm.check.apply.example.param.UserInputDTO;
import com.magic.liuzm.response.BizJson;
import com.magic.liuzm.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/5/28 13:54
 * @description 数据接口
 */
@RestController
@RequestMapping("/checkUltravires")
@Slf4j
public class CheckUltraviresController {

    /**
     * 删除信息
     */
    @GetMapping("/removeUser")
    @BizCheckUltravires(processor = UserUltraviresProcessor.class)
    public R removeUser(@RequestParam(value = "id") String userId) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }

    /**
     * 添加信息
     */
    @GetMapping("/addUser")
    @BizCheckUltravires(processor = UserUltraviresProcessor.class)
    public R addUser(@RequestParam(value = "name") String userName,@RequestParam(value = "age") Integer userAge) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }

    /**
     * 更新信息
     */
    @GetMapping("/updateUser")
    @BizCheckUltravires(processor = UserUltraviresProcessor.class)
    public R updateUser(@RequestBody UserInputDTO input) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }


}