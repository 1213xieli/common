package com.xieli.framework.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import com.xieli.framework.common.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class FwMessageUtils {

    private static final Logger logger = LoggerFactory.getLogger(FwMessageUtils.class);

    private static final Properties PROS;

    static {
        PROS = new Properties();
        try {
            ClassPathResource cpr = new ClassPathResource("common_messages.properties");
            InputStream is = cpr.getInputStream();
            PROS.load(is);

        } catch (IOException e) {
            logger.error("Read validation messages failed", e);
        }
    }

    private FwMessageUtils() {
    }

    public static String getMessageString(String code) {
    	if(StringUtils.isEmpty(code))
    		return null;
    	String value = PROS.getProperty(code);
        return value==null?code:value;
    }

    public static String getMessageString(String code, Object... args) {
        String message = getMessageString(code);
        if (message == null)
        	message = code;
        return MessageFormat.format(message, args);
    }

    public static Message getMessage(String code, Object... args) {
        return Message.message(code, getMessageString(code, args));
    }

}
