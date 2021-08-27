package com.lazy.common.utils;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.*;

/**
* @Author milelu
* @Description 公用类
* @Date 13:52 2020/5/26
*/
public class CommonUtils {

    /**
    * @Author milelu
    * @Description 判断字符串是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static boolean isNotEmptyString(String param){
        return param != null && !"".equals(param) && !"null".equals(param) && param.length() > 0;
    }
    /**
    * @Author milelu
    * @Description 判断数据是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static boolean isNotEmptyInteger(int param){
        return param > 0;
    }
    /**
    * @Author milelu
    * @Description 判断object是否为空，为空返回false，不为空返回true。
    * @Date 18:14 2020/5/28
    */
    public static boolean isNotEmptyObject(Object param){
        return null != param && !"".equals(param) && !"null".equals(param);
    }
    /**
    * @Author milelu
    * @Description 判断列表是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static <E> boolean isNotEmptyList(List<E> params){
        return params != null && params.size() > 0;
    }
    /**
    * @Author milelu
    * @Description 判断数组是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static boolean isNotEmptyArray(Object[] params){
        return params != null && params.length > 0;
    }
    /**
    * @Author milelu
    * @Description 判断集合是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static <E> boolean isNotEmptySet(Set<E> params){
        return params != null && !params.isEmpty();
    }
    /**
    * @Author milelu
    * @Description 判断集合是否为空，为空返回false，不为空返回true。
    * @Date 13:56 2020/5/26
    */
    public static <k, V> boolean isNotEmptyMap(Map<k,V> params){
        return params != null && !params.isEmpty() && params.size() > 0;
    }
    /**
    * @Author milelu
    * @Description 判断实体中必传字段是否为空，为空返回false，不为空返回true。 注意：当变量为0是默认为空
    * @Date 15:02 2020/5/27
    */
    public static <T> boolean isNotEmptyClass(T enety, String[] params) throws IllegalAccessException {
        Set<String> set = new HashSet<>(Arrays.asList(params));
        Class<?> enetyClass = enety.getClass();
        Field[] fields = enetyClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (set.contains(field.getName())){
                String value = field.get(enety).toString();
                if (value == null || "".equals(value) || "null".equals(value) || "0".equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
    * @Author milelu
    * @Description 判断hashMap中一些key是否为空，为空返回false，不为空返回true。
    * @Date 17:54 2020/5/28
    */
    public static <K,V> boolean isNotEmptyHashMapForKey(Map<K,V> map,String[] params){
        if (!isNotEmptyMap(map)){
            return false;
        }
        List<String> list = Arrays.asList(params);
        for (String keys : list) {
            if(!map.containsKey(keys) || !isNotEmptyObject(map.get(keys))){
                return false;
            }
        }
        return true;
    }

    /**
     * 断言字符串不为null，不为空，不是全空格
     * @param cs 判断的字符串
     * @return 断言正确返回true,否则为false
     */
    public static Boolean BeNotBlank(CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }

    public static Boolean BeBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    /**
     * 断言集合不为null且不为空
     * @param coll 判断的集合
     * @return 断言正确返回true,否则为false
     */
    public static <T> Boolean BeNotEmpty(Iterable<T> coll) {
        return !IterableUtils.isEmpty(coll);
    }

    /**
     * 断言数组不能为空
     * @param arr 判断的数组
     * @return 断言正确返回true,否则为false
     */
    public static <T> Boolean BeNotEmpty(T[] arr) {
        return ArrayUtils.isNotEmpty(arr);
    }
    /**
     * 断言map不为null且不为空
     * @param map 判断的map
     * @return 断言正确返回true,否则为false
     */
    public static <K, V> boolean BeNotEmpty(Map<K, V> map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * 断言对象不为null
     * @param reference 判断的对象
     * @return 断言正确返回true,否则为false
     */
    public static <T> boolean BeNotNull(T reference) {
        if (reference == null) {
            return false;
        }
        return true;
    }

    public static <T> boolean BeNull(T reference) {
        return !BeNotNull(reference);
    }

    public static <T> Boolean BeEmpty(Iterable<T> coll) {
        return !BeNotEmpty(coll);
    }
}
