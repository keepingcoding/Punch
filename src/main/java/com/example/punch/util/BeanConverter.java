package com.example.punch.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class BeanConverter {

    public static <S, D> D convert(S src, Class<D> clazz) {
        return JSON.parseObject(JSON.toJSONString(src), clazz);
    }

    public static <S, D> D convert(S src, TypeReference<D> tref) {
        return JSON.parseObject(JSON.toJSONString(src), tref);
    }
}
