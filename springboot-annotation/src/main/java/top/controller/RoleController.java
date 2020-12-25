package top.controller;

import top.model.query.RoleQuery;
import top.model.response.RespResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author bz
 * @date 2020/9/19
 */
@RestController
@RequestMapping("/role")
@Validated
public class RoleController {

    /**
     * 此处使用 @Validated 与 @Valid 是一样的效果
     * @param query
     * @return
     */
    @GetMapping("/getRoleList")
    public RespResult getRoleList(@Valid RoleQuery query){
        return new RespResult(200,"success");
    }

    /**
     * 此处设置@NotNull 注解，生效必须是全局类上配置 @Validated ，否则也不生效
     * @Valid 不管是配置在方法上还是类上都无法生效
     * @param age
     * @return
     */
    @GetMapping("/getRoleList2")
    public RespResult getRoleList2( @NotNull Integer age){
        System.out.println(age);
        return new RespResult(200,"success");
    }


    @GetMapping("/getRoleList3")
    public RespResult getRoleList3(@Valid  RoleQuery query){
        System.out.println(query);
        return new RespResult(200,"success");
    }

    /**
     *  size 只有不为空的情况下才去校验长度
     * @param rolename
     * @return
     */
    @GetMapping("/getRoleList4")
    public RespResult getRoleList4(@NotNull @Size(min = 3,max = 10) String rolename){
        System.out.println(rolename);
        return new RespResult(200,"success");
    }
}
