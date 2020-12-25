package top.controller;

import top.annotation.SwaggerNotes;
import top.enums.subcodes.UserSubCode;
import top.model.entity.UserEntity;
import top.model.param.AddUserParam;
import top.model.param.UpdateUserParam;
import top.model.param.UserSexParam;
import top.model.response.ResponseInfo;
import top.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * 加入插件之后的controller,配置文档可以简化很多
 *
 * @author lgs
 */
@RestController
@RequestMapping("/user")
@Validated
@Api(tags = "UserController", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @SwaggerNotes(subCodeType = UserSubCode.class, codeType = {"14021"}, title = "新增用户")
    @ApiResponses({@ApiResponse(response = UserEntity.class, code = 200, message = "ok")})
    @PostMapping("/addUser")
    public ResponseInfo addUser(@RequestBody @Valid AddUserParam param) {
        return userService.addUser(param);
    }

    @SwaggerNotes(subCodeType = UserSubCode.class, codeType = {"14022"}, title = "查询所有用户集合")
    @ApiResponses({@ApiResponse(response = UserEntity.class, code = 200, message = "ok")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "页码", example = "1", required = true),
            @ApiImplicitParam(name = "size", value = "页面大小", example = "20", required = true)
    })
    @GetMapping("/findUserList")
    public ResponseInfo findUserList(@Valid UserSexParam param) {
        return userService.findUserList(1, 10);
    }

    @SwaggerNotes(subCodeType = UserSubCode.class, codeType = {"14023"}, title = "修改用户")
    @PutMapping("/updateUser")
    public ResponseInfo updateUser(@RequestBody @Valid UpdateUserParam param) {
        return userService.updateUser(param);
    }

    @SwaggerNotes(subCodeType = UserSubCode.class, codeType = {"14024"}, title = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", example = "5", required = true)
    })
    @DeleteMapping("/deleteUser")
    public ResponseInfo deleteUser(@RequestParam("uid") @Min(value = 1, message = "用户id格式错误") Long uid) {
        return userService.deleteUser(uid);
    }
}
