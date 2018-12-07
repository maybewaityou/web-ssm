package com.meepwn.ssm.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author MeePwn
 */
@Component
public class RedisManager {

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 判断是否存在 key
     *
     * @param key key
     * @return 是否存在
     */
    public boolean exists(Serializable key) {
        Boolean isExist = redisTemplate.hasKey(key);
        if (isExist == null) {
            return false;
        } else {
            return isExist;
        }
    }

    /**
     * 插入缓存
     *
     * @param key   key
     * @param value value
     * @return 是否成功
     */
    public boolean set(Serializable key, Object value) {
        return set(key, value, null);
    }

    /**
     * 插入缓存
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间 (单位: s)
     * @return 是否成功
     */
    public boolean set(Serializable key, Object value, Long expireTime) {
        boolean success = false;
        try {
            ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            if (expireTime != null) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            success = true;
        } catch (Exception e) {
            LogUtils.e("{}", e);
        }
        return success;
    }

    /**
     * 获取缓存
     *
     * @param key key
     * @return 缓存数据
     */
    public Object get(Serializable key) {
        ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
        LogUtils.d("==== 命中 ====");
        return valueOperations.get(key);
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return 是否成功
     */
    public boolean remove(Serializable key) {
        boolean success = false;
        if (exists(key)) {
            redisTemplate.delete(key);
            success = true;
        }
        return success;
    }

    /**
     * 设置 redis 模板
     *
     * @param redisTemplate redis 模板
     */
    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
