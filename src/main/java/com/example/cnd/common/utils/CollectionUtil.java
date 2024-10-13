package com.example.cnd.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> CollectionUtil </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
public class CollectionUtil {
    /**
     * Map origins by mapper
     *
     * @param origins Origin list
     * @param mapper The mapper
     * @return List
     */
    public static <T, R> List<R> map(List<T> origins, Function<? super T, ? extends R> mapper) {
        if (CommonUtil.isNullOrEmpty(origins))
            return new ArrayList<>();
        return origins.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
    /**
     * Map origins by mapper then distinct
     *
     * @param origins Origin list
     * @param mapper The mapper
     * @return List
     */
    public static <T, R> List<R> mapThenDistinct(List<T> origins, Function<? super T, ? extends R> mapper) {
        if (CommonUtil.isNullOrEmpty(origins))
            return new ArrayList<>();
        return origins.stream()
                .map(mapper)
                .distinct()
                .collect(Collectors.toList());
    }
}