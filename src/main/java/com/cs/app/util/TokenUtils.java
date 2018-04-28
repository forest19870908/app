package com.cs.app.util;

import com.cs.app.model.Tokenlog;

import java.util.HashMap;
import java.util.Map;

/**
 * token相关信息
 */
public class TokenUtils {
    public static Map<String,Tokenlog> tokenMaps =new HashMap<String,Tokenlog>();//存储token信息（缓存）

    /**
     * 根据token获得token对象
     * @param token
     * @return
     */
    public static Tokenlog getToken(String token){
        if(tokenMaps.containsKey(token)){
            return tokenMaps.get(token);
        }
        return null;
    }

    public static void putToken(String token,Tokenlog tokenlog){
        tokenMaps.put(token,tokenlog);
    }
}
