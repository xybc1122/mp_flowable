package com.mq.demo.utils;

import com.mq.demo.sys.entity.Tenant;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListUtils
 * Description TODO
 * @Author 陈恩惠
 * @Date 2019/6/10 13:56
 **/
public class ListUtils {

    /**
     * 判断传进来的List 状态
     *
     * @param obj
     * @return
     */
    public static boolean listIsNull(List<?> obj) {
        return obj != null && obj.size() > 0;
    }


    /**
     * 查询主表  ids
     *
     * @param list
     * @return
     */
    public static List selIds(List<?> list) {
        List<Object> l = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Tenant) {
                Tenant tenant = (Tenant) obj;
                l.add(tenant.getTenantId());
            }
        }
        return l;
    }
}
