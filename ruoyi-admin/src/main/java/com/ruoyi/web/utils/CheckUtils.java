package com.ruoyi.web.utils;

import java.util.HashMap;
import java.util.Map;

public class CheckUtils {

    public final static Map<String,String> scoreMap = new HashMap();

    static {
        scoreMap.put("01", "0-650");
        scoreMap.put("02", "650-750");
        scoreMap.put("03", "750-900");
        scoreMap.put("04", "900-1000");
    }
}
