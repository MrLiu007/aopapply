package com.magic.liuzm.utils;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author zemin.liu
 * @date 2020/10/21 13:40
 * @description TraceId工具类
 */
@Log4j2
public class TraceUtil {

    public final static String MDC_TRACE_ID = "traceId";

    /**
     * 设置traceId到logBack的MDC中
     */
    public static void resetTraceID(){
        try {
            MDC.put(MDC_TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
        } catch (Exception ex) {
           log.error("异常-MDC-resetMDC异常", ex);
        }
    }

    /**
     * 从logBack的MDC中取traceId
     */
    public static String getTraceID(){
        String traceId = null;
        try {
            traceId = MDC.get(MDC_TRACE_ID);
        } catch (Exception ex) {
            log.error("异常-MDC-获取TraceID异常", ex);
        }
        if(StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        return traceId;
    }

    /**
     * 从logBack的MDC中删除traceId
     */
    public static void removeTraceID(){
        try {
            MDC.remove(MDC_TRACE_ID);
        } catch (Exception ex) {
            log.error("异常-MDC-清除TraceID异常", ex);
        }
    }
}
