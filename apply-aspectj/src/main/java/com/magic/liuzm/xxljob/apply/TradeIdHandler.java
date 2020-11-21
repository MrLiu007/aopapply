package com.magic.liuzm.xxljob.apply;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author zemin.liu
 * @date 2020/11/09 10:06
 * @description XXL-Job执行器
 */
@Component
public class TradeIdHandler {

    /**
     * xxl-job 调用入口
     */
    @XxlJob("onJobHandler")
    public ReturnT<String> onJobHandler(String param) throws Exception {
        // 具体业务逻辑
        return ReturnT.SUCCESS;
    }
}
