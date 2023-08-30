package com.example.noProject.service.redis;

import com.example.noProject.constants.BussinessConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/***
 * redis服务类，默认“StringRedisSerializer” 字符串形式的序列化
 */
@Component
public class RedisService {
    @Resource
    private RedisTemplate redisTemplate;




    /**
     * 重置所有序列化格式为“字符串形式”
     */
    private void resetSerializer(){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }


    public Object getObjectFormSessionByKey(HttpServletRequest request, String hashField){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        Object obj=null;
        if(request==null){
            return null;
        }
        String token = request.getHeader(BussinessConstants.ACCESS_TOKEN);
        if(token!=null){
            //开启redis，用户数据放在redis中，从redis中获取
            if(redisTemplate.opsForHash().hasKey(token,hashField)){
                //redis中存在，拿出来使用
                obj=redisTemplate.opsForHash().get(token,hashField);
                //重置过期时间
                redisTemplate.expire(token, BussinessConstants.MAX_SESSION_IN_SECONDS, TimeUnit.SECONDS);
            }
        }
        return obj;
    }

    public Object getJsonObjectFormSessionByKey(HttpServletRequest request, String hashField){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        Object obj=null;
        if(request==null){
            return null;
        }
        String token = request.getHeader(BussinessConstants.ACCESS_TOKEN);
        if(token!=null){
            //开启redis，用户数据放在redis中，从redis中获取
            if(redisTemplate.opsForHash().hasKey(token,hashField)){
                //redis中存在，拿出来使用
                obj=redisTemplate.opsForHash().get(token,hashField);
                //重置过期时间
                redisTemplate.expire(token, BussinessConstants.MAX_SESSION_IN_SECONDS, TimeUnit.SECONDS);
            }
        }
        return obj;
    }


    public void storageObject(String token, String key, Object obj){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.opsForHash().put(token,key,obj);
        redisTemplate.expire(token, BussinessConstants.MAX_SESSION_IN_SECONDS, TimeUnit.SECONDS);
    }

    public Boolean removeObjectBySession(HttpServletRequest request){
        if(request == null){
            return false;
        }
        String token = request.getHeader(BussinessConstants.ACCESS_TOKEN);
        if(token != null){
            return redisTemplate.delete(token);
        }
        return false;
    }

}
