package bcp.exchange.rate.exception;

import org.apache.commons.lang3.StringUtils;

public class Errors { 

    public static String getMessage(Exception e) {
        return getMessage(e, null);
    }
    
    public static String getMessage(Throwable e) {
        return getMessage(e, null);
    }

    public static String getMessage(Exception e, String infoAdicional) {
        return "[ERROR] " + e.getMessage() + (StringUtils.isNotBlank(infoAdicional) ? ";[ADITIONAL] " + StringUtils.trimToEmpty(infoAdicional) : StringUtils.EMPTY);
    }
    
    public static String getMessage(Throwable e, String infoAdicional) {
        return "[ERROR] " + e.getMessage() + (StringUtils.isNotBlank(infoAdicional) ? ";[ADITIONAL] " + StringUtils.trimToEmpty(infoAdicional) : StringUtils.EMPTY);
    }
}