package org.app.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.String.join;

public class LogsUtil {
    public static final String LOGS_PATH = "test-outputs/logs";

    private LogsUtil(){
        super();
    }

    public static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
        //to return where the log was called not this class every time

    }
    //the three dots means you can pass more than one message
    public static void trace(String... message){
        logger().trace(join(" ",message));
    }
    public static void debug(String... message){
        logger().debug(join(" ",message));
    }
    public static void info(String... message){
        logger().info(join(" ",message));
    }
    public static void warn(String... message){
        logger().warn(join(" ",message));
    }
    public static void error(String... message){
        logger().error(join(" ",message));
    }
    public static void fatal(String... message){
        logger().fatal(join(" ",message));
    }
}
