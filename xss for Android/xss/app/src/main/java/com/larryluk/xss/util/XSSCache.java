package com.larryluk.xss.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Larry on 2017/8/19.
 */

public class XSSCache {
    private static Map<Enum, Object> cache;

    private XSSCache () {
        cache = new HashMap<>();
    }

    public static void put(Enum key, Object value) {
        if( cache == null )
            new XSSCache();

        cache.put(key, value);
    }

    public static Object get (Enum key) {
        if( cache == null )
            new XSSCache();

        return cache.get(key);
    }
}
