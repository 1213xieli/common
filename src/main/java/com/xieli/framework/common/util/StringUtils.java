package com.xieli.framework.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author L.W.L
 *
 */
public class StringUtils {
	/**
	 * 检查字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return null == str ? true
				: ("".equals(str.trim()) ? true : (str.length() <= 0 ? true : false));
	}

	/**
	 * 检查字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 检查字符串是否相同
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqual(String str1, String str2) {
		return null == str1 ? (str2 == null ? true : false) : (str1.equals(str2) ? true : false);
	}

	/**
	 * 检查字符串是否满足指定正则
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean isMatch(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);

		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 类型转换从String转向Date 格式由String类型的strFromat定义；默认格式为yyyy-MM-dd
	 * @param oldString  时间字符串
	 * @param strFromat  格式化
	 * @return newDate 日期
	 */
	public static Date toDate(String oldString, String strFromat) {
		try {
			if (null == strFromat) {
				strFromat = "yyyy-MM-dd";
			}
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(strFromat);
			Date newDate = null;
			if ((null != oldString) && (!oldString.equals(""))) {
				newDate = bartDateFormat.parse(oldString);
			}
			return newDate;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 类型转换从Date转向String 格式由String类型的strFromat定义；默认格式为yyyy-MM-dd
	 * @param date  日期
	 * @param strFromat  格式化
	 * @return
	 */
	public static String dateToString(Date date, String strFromat) {
		try {
			if (null == strFromat) {
				strFromat = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(strFromat);

			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将字符器转成整数，如果转换出错，返回空
	 * @param numString 数字字符串
	 * @return
	 */
	public static Integer toInt(String numString) {

		try {
			return Integer.parseInt(numString);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 通过正则表达式的方式获取字符串中指定字符的个数
	 * @param text 指定的字符串
	 * @return 指定字符的个数
	 */
	public static int isInclude(String text, String point) {
		// 根据指定的字符构建正则
		Pattern pattern = Pattern.compile(point);
		// 构建字符串和正则的匹配
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		// 循环依次往下匹配
		while (matcher.find()) { // 如果匹配,则数量+1
			count++;
		}
		return count;
	}

	/**
	 * 获取随机数
	 * @param num 随机数的位数
	 * @return
	 */
	public static String getRandomNumber(int num) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		if(num<=0) return "";
		for (int i = 0; i < num; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}


	/**
	 * 获取随机字母
	 * @param num 随机字母的个数
	 * @return
	 */
	public static String getRandomChar(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			char c = (char) (int) (Math.random() * 26 + 97);
			sb.append(c);
		}
		return sb.toString();
	}

	//生成随机数字和字母,
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //length为几位密码
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String join(Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(Iterator<?> iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return (first == null ? "" : first.toString());
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }

    /**
	 * Utf8URL编码
	 * @param s
	 * @return
	 */
	public static String utf8URLencode(String text) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			}
			else {
				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return result.toString();
	}

	 /* *
	  * @description 显示标题
	  * @author xieli
	  * @date 2019/11/6
	  * @param []
	  * @return java.lang.String
	  **/
	 public static String moduleShowTitle(String sep, String... data)
	 {
	  if (data == null || data.length <= 0)
	   return null;

	  StringBuilder str = new StringBuilder();
	  for (String d : data) {
	   if (StringUtils.isEmpty(d))
	    continue;

	   str.append(d);
	   str.append(sep);
	  }

	  str.deleteCharAt(str.length()-1);
	  return str.toString();
	 }

}
