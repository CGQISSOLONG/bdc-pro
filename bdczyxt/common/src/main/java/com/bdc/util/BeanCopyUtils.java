package com.bdc.util;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 类名：BeanCopyUtils
 * @Description 功能说明：实体类复制
 */
public final class BeanCopyUtils extends BeanUtil{
    private BeanCopyUtils() {
    }
    public static <T,P> P copyProperties(T source, Class<P> target) {
        try {
            P p=target.newInstance();
            BeanUtil.copyProperties(source, p);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 复制集合
     * @param source
     * @param target
     * @param <T>
     * @param <P>
     * @return
     */
    public static <T,P> List<P> copyProperties(List<T> source,Class<P> target) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        List<P> dtos = new ArrayList<>(source.size());
        source.stream().forEach(user1 -> {
            dtos.add(copyProperties(user1,target));
        });
        return dtos;
    }


}
