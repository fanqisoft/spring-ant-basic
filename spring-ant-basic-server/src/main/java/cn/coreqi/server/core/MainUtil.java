package cn.coreqi.server.core;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

public class MainUtil {
    /**
     * 获取分页插件排序字符串
     * @param sort
     * @return
     */
    public static String GetPageHelperOrdersStr(Sort sort) {
        String resStr = "";
        Iterator<Sort.Order> orderIterator = sort.iterator();
        List<Sort.Order> orderList = Lists.newArrayList(orderIterator);
        for (Sort.Order order : orderList) {
            resStr += CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, order.getProperty())
                    + " "
                    + order.getDirection()
                    + ",";
        }
        resStr = CharMatcher.anyOf(",").trimTrailingFrom(resStr);
        return resStr;

    }
}
