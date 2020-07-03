package com.xinyuan.ipv6.core.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 单位工具
 * 主要去掉返回的数据中的单位
 * Created by pen on 2018/3/20.
 */
public class UnitUtil {
    /**
     * 去掉单位提取数字，包括小数点和整数
     *
     * @param s
     * @return
     */
    public static BigDecimal stringToBigDecimal(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String regEx = "([\\d.\\d]+)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        if (m.find()) {
            return new BigDecimal(m.group(0));
        }
        return null;
    }
}
