package top.util;

/**
 * subCode工具类
 *
 * @author lgs
 */
public class SubCodeUtils {

    enum EnvType {
        /**
         * 开发
         */
        dev(1),
        /**
         * 测试
         */
        test(2),
        /**
         * 预发布
         */
        stage(3),
        /**
         * 正式
         */
        prod(4);

        /**
         * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
         */
        EnvType(int value) {
            this.value = value;
        }


        /**
         * 分析师主域名编号
         */
        private static String masterHostId = "14";


        private final int value;

        public int getValue() {
            return value;
        }
    }

    private static EnvType activeEnv = EnvType.prod;

    /**
     * 获取环境编码
     *
     * @return
     */
    public static String getEnvPrefixOfSubCode() {
        EnvType envType = EnvType.prod;
        for (EnvType value : EnvType.values()) {
            if (value == activeEnv) {
                envType = value;
                break;
            }
        }
        return envType.getValue() + "";
    }

}

