package com.magic.liuzm.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

/**
 * @author zemin.liu
 * @date 2020/6/10 15:09
 * @description redis 实现锁
 */
@Service
public class RedisLockDao {

    private final static String okResult = "OK";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * unlock操作
     *
     * @param key     key
     * @param lockValue value
     */
    public boolean unlock(String key, String lockValue) {
        try {
            // 值
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            String value = valueOps.get(key);
            // 如果是加锁者则删除锁,如果不是不处理
            if (value != null && value.equals(lockValue)) {
                redisTemplate.delete(lockValue);
            }
            return true;
        } catch (Throwable e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * lock操作
     *
     * @param key     key
     * @param seconds value
     * @param value   seconds 秒
     */
    public boolean lock(String key,String value,int seconds) {
        if(StringUtils.isEmpty(key)){
            return false;
        }
        return (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    result = ((JedisCluster) nativeConnection).set(key, value, SetParams.setParams().nx().ex(seconds));
                }
                // 单机模式
                if (nativeConnection instanceof Jedis) {
                    result = ((Jedis) nativeConnection).set(key, value, SetParams.setParams().nx().ex(seconds));
                }
                return okResult.equals(result);
            }
        });
    }
}
