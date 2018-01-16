package com.bdqn.huanxun.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
public class DataLineUtil {
    public static List<Map<String, Object>> getXArray(List<Map<String, Object>> mmm) {
        List<Map<String, Object>> list = new ArrayList<>();
        if(null!=mmm){
            for (Map<String, Object> map : mmm) {
                Map<String, Object> m = new HashMap<>();
                m.put("value", map.get("value"));
                list.add(m);
            }
        }
        return list;
    }

    public static List<Object> getYArray(List<Map<String, Object>> mmm) {
        List<Object> list = new ArrayList<>();
        if(mmm!=null){
            for (Map<String, Object> map : mmm) {
                Object y = map.get("total");
                list.add(y);
            }
        }
        return list;
    }

    public static Map<String, Object> getLineGramData(List<Map<String, Object>> mmm) {
        Map<String, Object> map = new HashMap<>();
        map.put("xArray", getXArray(mmm));
        map.put("yArray", getYArray(mmm));
        return map;
    }
}
