package top.controller;

import top.annotation.MyLog;
import top.model.query.UserQuery;
import top.model.response.RespResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.*;
import java.util.ArrayList;

/**
 * @author bz
 * @date 2020/9/19
 */
@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    /**
     * 总结：
     * 1.参数实体 UserQuery 必须有 set 方法，否则属性无法注入
     * 2.实体内部写了参数校验，validated 必须设置在参数入口才能生效，设置在类上无法生效
     */
    @GetMapping("/getUserList")
    public RespResult<?> getUserList(@Validated UserQuery query){
        System.out.println(query);
        return new RespResult<>(200,"success");
    }

    @GetMapping("/getUserList2")
    public RespResult getUserList2(@NotNull(message = "用户名不能为空") String username){
        System.out.println(username);
        return new RespResult<>(200,"success");
    }

    @GetMapping("/getUserList3")
    public RespResult getUserList3(@Min(value = 10,message = "最小值大于10") @Max(value = 20,message = "不能大于20") @NotNull(message = "age 不能为空") Integer age){
        System.out.println(age);
        return new RespResult<>(200,"success");
    }

    @GetMapping("/getUserList4")
    public RespResult getUserList4(@NotNull(message = "email 不能为空") String email){
        System.out.println(email);
        return new RespResult<>(200,"success");
    }

    /**
     * 必须符合邮箱合适才能通过匹配
     * @param email
     * @return
     */
    @GetMapping("/getUserList5")
    public RespResult getUserList5(@Email(message = "email 格式错误")  String email){
        System.out.println(email);
        return new RespResult<>(200,"success");
    }

    /**
     *
     * 所以 validated 能够实现所以的校验 ，而 valid 只是辅助validated 简化实体校验
     */
    @GetMapping("/getUserList6")
    public RespResult getUserList6(@Size(message = "size 校验",min = 10,max = 20) ArrayList<Integer> ages){
        System.out.println(ages);
        System.out.println(ages);
        return new RespResult<>(200,"success");
    }


    /**
     *  测试切面
     */
    @MyLog
    @GetMapping("/login")
    public void login(){
        System.out.println("【Login】:用户进行登录");
    }
}
