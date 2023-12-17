package com.ruoyi.web.utils;

import java.util.HashMap;
import java.util.Map;

public class CheckUtils {

    public final static Map<String,String> scoreMap = new HashMap();

    public final static Map<String,String> personNumsMap = new HashMap();

    public final static Map<String,String> enAgesMap = new HashMap();

    static {
        scoreMap.put("01", "0-650");
        scoreMap.put("02", "650-750");
        scoreMap.put("03", "750-900");
        scoreMap.put("04", "900-1000");

        personNumsMap.put("01", "01");
        personNumsMap.put("02", "02");
        personNumsMap.put("03", "03");
        personNumsMap.put("04", "04");

        enAgesMap.put("01", "0-1");
        enAgesMap.put("02", "1-3");
        enAgesMap.put("03", "3-5");
        enAgesMap.put("04", "5-10");
        enAgesMap.put("05", "10-100");
    }
}
