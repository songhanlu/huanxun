package com.bdqn.huanxun.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/11.
 */
public class DataPieUtil {

    public static void addEle(List<Map<String, Object>> list,
                              String name,
                              Object value){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("value", value);
        list.add(map);
    }
}
