package com.magic.liuzm.check.apply.example.param;

import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.check.annotation.BizCheckParam;
import com.magic.liuzm.check.annotation.CheckParam;
import com.magic.liuzm.check.annotation.CheckParams;
import com.magic.liuzm.check.enums.CheckRuleEnum;
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
@RequestMapping("/check")
@Slf4j
public class CheckParamController {

    /**
     * 删除信息
     */
    @GetMapping("/removeUser")
    @CheckParam(value = CheckRuleEnum.NotEmpty, argName = "id", msg = "用户编号不能为空")
    public R removeUser(@RequestParam(value = "id") String userId) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }

    /**
     * 添加信息
     */
    @GetMapping("/addUser")
    @CheckParams({
            @CheckParam(value = CheckRuleEnum.NotEmpty, argName = "name", msg = "用户名称不能为空"),
            @CheckParam(value = CheckRuleEnum.Range, argName = "age", express = "0,200",msg = "用户年龄必须在0-200以内")
    })
    @BizCheckParam(available = true,bizType = BizEnum.USER)
    public R addUser(@RequestParam(value = "name") String userName,@RequestParam(value = "age") Integer userAge) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }

    /**
     * 更新信息
     */
    @GetMapping("/updateUser")
    @CheckParams({
            @CheckParam(value = CheckRuleEnum.NotEmpty, argName = "input.id", msg = "用户编号不能为空"),
            @CheckParam(value = CheckRuleEnum.NotEmpty, argName = "input.name", msg = "用户名称不能为空")
    })
    @BizCheckParam(available = false)
    public R updateUser(@RequestBody UserInputDTO input) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }


}