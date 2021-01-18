package captcha.controller;


import captcha.properties.ValidateCodeProperties;
import captcha.util.RedisUtils;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author bz
 * @date 2020/9/14
 */
@RestController
public class ValidateCodeController {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 中文类型
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/createChineseImage")
    public void createChineseImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        String key = request.getSession().getId();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        Captcha captcha = new ChineseCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        redisUtils.set(properties.getPrefix()+key,captcha.text(),properties.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 算数类型
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/createArithmeticImage")
    public void createArithmeticImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        String key = request.getSession().getId();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        Captcha captcha = new ArithmeticCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
        redisUtils.set(properties.getPrefix()+key,captcha.text(),properties.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     *
     * 数字+英文
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/createSpecImage")
    public void createSpecImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        String key = request.getSession().getId();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        Captcha captcha = new SpecCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
        redisUtils.set(properties.getPrefix()+key,captcha.text(),properties.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     *
     * 数字+英文
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/createChineseGifImage")
    public void createChineseGifImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        String key = request.getSession().getId();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        Captcha captcha = new ChineseGifCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
        redisUtils.set(properties.getPrefix()+key,captcha.text(),properties.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     *
     * 数字+英文
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/createGifCaptchaImage")
    public void createGifCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        String key = request.getSession().getId();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        Captcha captcha = new GifCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
        redisUtils.set(properties.getPrefix()+key,captcha.text(),properties.getTime());
        captcha.out(response.getOutputStream());
    }




    @GetMapping("/check/{code}")
    public void check(@PathVariable(name = "code") String code, HttpServletRequest request) throws Exception{
        ValidateCodeProperties properties = new ValidateCodeProperties();
        HttpSession session = request.getSession();
        Object codeInRedis = redisUtils.get(properties.getPrefix()+session.getId());
        if(StringUtils.isEmpty(code)){
            System.out.println("请输入验证码");
        }
        if(codeInRedis == null){
            System.out.println("验证码已经过期");
        }
        if(String.valueOf(codeInRedis).equalsIgnoreCase(code)){
            System.out.println("验证通过");
        }else{
            System.out.println("验证未通过");
        }
    }

    @GetMapping("/getSessionId")
    public String getSessionId(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return session.getId();
    }

}
