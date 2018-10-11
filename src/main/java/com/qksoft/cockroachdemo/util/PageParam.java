package com.qksoft.cockroachdemo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储页面参数，用于后续的实体部分更新
 */
public final class PageParam {
    private static final ThreadLocal<Map<String, List<String>>> PAGE_PARAM_NAMES = new ThreadLocal<>(); //存储页面参数名
    private static final String DEFALT_KEY = "_head"; //默认key值，用于存储表体外的其他参数名

    /**
     * 内容依附于线程，无需实例化
     */
    private PageParam() {

    }

    /**
     * 获取参数名的List
     *
     * @param key 两种取值，表头类型参数名key固定为_head，表单类型参数名为表单对应的Bean的类名
     * @return 参数名的List
     */
    public static List<String> get(String key) {
        Map<String, List<String>> nameMap = PAGE_PARAM_NAMES.get();

        if (nameMap != null) {
            return nameMap.get(key);
        } else {
            return null;
        }
    }

    /**
     * 获取列表外的参数名(单据的表单头)
     *
     * @return 列表外的参数名
     */
    public static List<String> get() {
        Map<String, List<String>> nameMap = PAGE_PARAM_NAMES.get();

        if (nameMap != null) {
            return nameMap.get(DEFALT_KEY);
        } else {
            return null;
        }
    }

    /**
     * 记录参数名
     *
     * @param key   参数名对应的key
     * @param value 参数名
     */
    public static void add(String key, String value) {
        Map<String, List<String>> nameMap = PAGE_PARAM_NAMES.get();

        if (nameMap == null) {
            nameMap = new HashMap<>();
            PAGE_PARAM_NAMES.set(nameMap);
        }

        if (!nameMap.containsKey(key)) {
            nameMap.put(key, new ArrayList<>());
        }
        nameMap.get(key).add(value);
    }

    /**
     * 记录参数名
     *
     * @param value 参数名
     */
    public static void add(String value) {
        add(DEFALT_KEY, value);
    }

    /**
     * 清理上次线程参数残留
     */
    public static void clear() {
        Map<String, List<String>> nameMap = PAGE_PARAM_NAMES.get();
        if (nameMap != null) {
            nameMap.clear();
        }
    }
}