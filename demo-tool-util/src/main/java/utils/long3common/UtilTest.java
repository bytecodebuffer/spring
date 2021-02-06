package utils.long3common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.IEEE754rUtils;

import java.nio.charset.StandardCharsets;

/**
 *
 * common.long3 工具包测试类型
 * @author bz
 * @date 2021/2/6
 */
@Slf4j
public class UtilTest {

    /**
     * 随机生成指定长度字符串
     * a-z A-Z 0-9
     */
    public static void randomString(){
        for(int i= 0;i<Byte.MAX_VALUE;i++) {
            String key = RandomStringUtils.randomAlphanumeric(15);
            System.out.println(key);
        }
    }

    public static void stringUtils(){
        StringUtils.isNotBlank("");
        StringUtils.equalsIgnoreCase("","");
    }

    public static void arrayUtils(){

    }


    public static void classUtils(){

    }

    public static void systemUtils(){
        String hostName = SystemUtils.getHostName();
        log.info(hostName);

        String username = SystemUtils.getUserName();
        log.info(username);
    }

    public static void ieeeUtils(){
        Double result = IEEE754rUtils.max(1.1);
        log.info(result.toString());
    }

    public static void main(String[] args) {
        ieeeUtils();

    }
}
