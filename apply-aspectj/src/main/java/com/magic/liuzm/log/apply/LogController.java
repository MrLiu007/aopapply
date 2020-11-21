package com.magic.liuzm.log.apply;

import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.cache.apply.example.UserOutputDTO;
import com.magic.liuzm.log.annotation.Log;
import com.magic.liuzm.response.BizJson;
import com.magic.liuzm.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zemin.liu
 * @date 2020/5/28 13:54
 * @description 数据接口
 */
@RestController
@RequestMapping("/log")
public class LogController {

    /**
     * 查询信息
     */
    @GetMapping("/queryUser")
    @Log(bizType = BizEnum.USER)
    public R<UserOutputDTO> queryUser(@RequestParam(value = "id") String userId) {
        // 业务逻辑
        UserOutputDTO result = null;

        if(result == null){
            // 暂无数据
            return R.success("暂无数据");
        }
        return R.data(BizJson.DEFAULT_SUCCESS_CODE,result,BizJson.DEFAULT_SUCCESS_MSG);
    }

}