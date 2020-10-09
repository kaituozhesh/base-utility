package com.ktz.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName OptionalUtility
 * @Description Optional工具类
 * @Author 开拓者-骚豪
 * @Date 2020/10/9 8:34
 * @Version V1.0.0
 **/
public class OptionalUtility {
    /**
     * 判断对象是否为空
     *
     * @param source 参与判断内容
     * @return 为空返回 true 否则返回false
     */
    public static boolean isEmpty(Object source) {
        return Optional.ofNullable(source).isPresent();
    }

    /**
     * 获取对象数据，如果为空则创建一个空对象
     *
     * @param source   源对象
     * @param supplier 如果为空返回空对象
     * @param <T>      返回对象类型
     * @return
     */
    public static <T> T get(T source, Supplier<T> supplier) {
        return Optional.ofNullable(source).orElseGet(supplier);
    }

    /**
     * 获取集合数据，如果集合为空返回空集合
     *
     * @param source 传入集合
     * @param <T>    传入集合类型
     * @return 返回对于类型集合列表，如果为空返回 size = 0的集合
     */
    public static <T> List<T> getList(List<T> source) {
        return Optional.ofNullable(source).orElseGet(ArrayList::new);
    }

    /**
     * 判断传入对象是否为空，为空抛出异常
     *
     * @param source       参与判断的对象
     * @param throwMessage 如果参数为为空 抛出异常的message信息
     */
    public static void orElseThrow(Object source, String throwMessage) {
        Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException(throwMessage));
    }

    /**
     * 把String转成允许为空的Optional<Integer>
     *
     * @param source 待转换内容
     * @return 如果传入非数值类型字符串将返回Optional.empty()
     */
    public static Optional<Integer> stringToInt(String source) {
        try {
            return Optional.of(Integer.parseInt(source));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
