package com.magic.liuzm.utils;
import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 日志打印
 */
@Log4j2
public class LogUtil {

    /**
     * 入参数打印
     */
    public static void printBeforeLog(String bizType,String className,String methodName,String args) {
        log.info(String.format("%s-,%s.,%s,(%s)", bizType, className, methodName, args));
    }

    /**
     * 出参数打印
     */
    public static void printAfterLog(String bizType,String className,String methodName,
                                String args,String result,
                                Integer errorCode,String errorMsg,long time) {
        if(errorCode == null){
            log.info(String.format("%s-,%s.,%s,(%s),result:%s,--useTime:%s ms",
                    bizType, className, methodName, args, result, time));
        }else {
            log.info(String.format("%s-,%s.,%s,(%s),--useTime:%s ms,--errorCode:%s,--errorMsg:%s",
                    bizType, className, methodName, args, time, errorCode, errorMsg));
        }
    }
}
