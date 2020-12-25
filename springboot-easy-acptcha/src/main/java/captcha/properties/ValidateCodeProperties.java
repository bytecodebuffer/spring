package captcha.properties;

import lombok.Data;

/**
 * @author bz
 * @date 2020/9/14
 */
@Data
public class ValidateCodeProperties {

    /**
     * 验证码前缀
     */
    private String prefix = "key_prefix_";

    /**
     * 验证码有效时间
     */
    private Long time = 120L;

    /**
     * 验证码类型
     */
    private String type = ImageType.PNG;
    /**
     * 图片宽度 px
     */
    private Integer width = 130;
    /**
     * 图片长度 px
     */
    private Integer height = 48;
    /**
     * 验证码位数
     */
    private Integer length = 4;
    /**
     * 验证码类型
     * 1.数字+字母
     * 2.数字
     * 3.字母
     */
    private Integer charType = 2;

}
