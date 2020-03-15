package com.fast.common.core.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 高频使用工具类
 * @author zhouzhou
 * @date 2020-03-11 15:02
 */
public class ToolUtil{
	
	
	/**
     * 获取随机字符,自定义长度
     *
     * @author zhouzhou
     * 2020-03-11 15:07
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
	
	/**
	 * 判断对象是否为空  true 不为空
	 * @author zhouzhou
	 * @date 2020-03-11 15:07
	 */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 
     * 对象是否为空 true 为空
     * @author zhouzhou
     * @date 2020-03-11 15:09
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if ("".equals(o.toString().trim())) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List<?>) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map<?, ?>) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set<?>) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否存在空对象
     *
     * @author zhouzhou
     * @Date 2020-03-11 15:09
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否全是空对象
     *
     * @author zhouzhou
     * @date 2020-03-11 15:09
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 根据消息键和参数 获取消息 委托给Spring messageSource
     * 
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args){
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
    
    /**
	 * 
	 * 字节计算转换
	 * 
	 * <pre>
	 * StrUtil.convertFileSize(1024)   			= 1kB
	 * </pre>
	 * @author ZhouHuan 18774995071@163.com
	 * @time 2019-04-03 12:29
	 * @param size 字节大小
	 * @return 转换后大小字符串
	 *
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb)
        {
            return StrUtil.format("{} GB", NumberUtil.round((float) size / gb,2));
        }
        else if (size >= mb)
        {
            float f = NumberUtil.round((float) size / mb,2).floatValue();
            return StrUtil.format(f > 100 ? "{} MB" : "{} MB", f);
        }
        else if (size >= kb)
        {
            float f = NumberUtil.round((float) size / kb,2).floatValue();
            return StrUtil.format(f > 100 ? "{}  KB" : "{}  KB", f);
        }
        else
        {
            return StrUtil.format("{} B", size);
        }
	}
	
	public static String getMessage(Exception e){
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		return sw.toString();
	}
}