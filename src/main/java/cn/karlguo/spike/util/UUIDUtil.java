package cn.karlguo.spike.util;

import java.util.UUID;

/*
 * @Classname UUIDUtil
 * @Date 2021/5/26
 * @author karlguo
 * @Description
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
