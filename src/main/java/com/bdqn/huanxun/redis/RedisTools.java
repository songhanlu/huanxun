package com.bdqn.huanxun.redis;


import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by hp on 2018/1/16.
 */
public class RedisTools {
    public static void main(String[] args) {
        //addString("name","宋含露");

        //System.out.println(getString("name"));

        /*List<String> names = new ArrayList<>();
        names.add("宋含猪");
        names.add("宋晗狗");
        names.add("第三方");
        addList("names",names);*/

        //System.out.println(getList("names"));

        Set<String> keys = getAllKey();
        Iterator<String> i = keys.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }

    }

    public static void connectTest(){
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println(jedis.ping());
    }

    public static void addString(String key,String value) {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set(key, value);
        System.out.println("字符串保存成功！");
    }

    public static String getString(String key) {
        return new Jedis("127.0.0.1").get(key);
    }

    public static void addList(String key, List<String> names) {
        if(null!=names){
            Jedis jedis = new Jedis("127.0.0.1");
            for (String name : names) {
                jedis.lpush(key, name);
            }
        }
    }

    public static List<String> getList(String key) {
        Jedis jedis = new Jedis("127.0.0.1");
        List<String> list = jedis.lrange(key, 0, -1);
        return  list;
    }

    public static Set<String> getAllKey() {
        return new Jedis("127.0.0.1").keys("*");
    }
}
