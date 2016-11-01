
package com.pocketdigi.demo.common.converter;


import java.util.ArrayList;
import java.util.List;


/**
 * list容器转换器
 */
public class ListObjConverter {

    public static <T> List<T> convert(List<?> source, Class<T> clazz, PojoConverter.ForceMatch... forceMatchs) {
        List<T> target = new ArrayList<T>();
        try {
            if (source == null) {
                // AuditLog.biz(LogModule.CORE, "对象集合转换异常，来源对象集合为空");
                return target;
            }
            for (Object sourceObj : source) {
                target.add(PojoConverter.convert(sourceObj, clazz, forceMatchs));
            }
        } catch (Exception e) {
            // AuditLog.exp(LogModule.CORE, "对象集合转换异常", e);
        }
        return target;

    }
}
