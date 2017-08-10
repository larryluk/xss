package com.larryluk.xss.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by larryluk on 2017/8/10.
 */
public class StrategyCache {
    private static Map<String, Object> strategyCache;


    private StrategyCache() {
        strategyCache = new HashMap<>();
    }
}
