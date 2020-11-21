package com.magic.liuzm.cache.apply.example;

import com.magic.liuzm.cache.annotation.BizCache;
import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.response.BizJson;
import com.magic.liuzm.response.R;
import com.magic.liuzm.cache.enums.CacheOperateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/5/28 13:54
 * @description 数据接口
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    /**
     * 添加信息
     */
    @GetMapping("/addUser")
    @BizCache(bizType = BizEnum.USER,operateType = CacheOperateEnum.SET,cacheMapperClass = R.class)
    public R addUser(@RequestParam(value = "name") String userName) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }

    /**
     * 查询信息
     */
    @GetMapping("/queryUser")
    @BizCache(bizType = BizEnum.USER,operateType = CacheOperateEnum.GET,cacheMapperClass = R.class)
    public R<UserOutputDTO> queryUser(@RequestParam(value = "id") String userId) {
        // 业务逻辑
        UserOutputDTO result = null;

        if(result == null){
            // 暂无数据
            return R.success("暂无数据");
        }
        return R.data(BizJson.DEFAULT_SUCCESS_CODE,result,BizJson.DEFAULT_SUCCESS_MSG);
    }

    /**
     * 删除信息
     */
    @GetMapping("/removeUser")
    @BizCache(bizType = BizEnum.USER,operateType = CacheOperateEnum.REMOVE,cacheMapperClass = R.class)
    public R removeUser(@RequestParam(value = "id") String userId) {
        // 业务逻辑
        boolean result = true;

        return result == true ? R.success(BizJson.DEFAULT_SUCCESS_MSG) : R.fail(BizJson.DEFAULT_FAILURE_MSG);
    }


}