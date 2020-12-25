package top.model.param;

import top.enums.types.SexEnum;
import top.validator.protocol.EnumValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 修改用户参数类
 *
 * @author lgs
 */
@ApiModel(description = "修改用户参数类")
public class UpdateUserParam {

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", example = "壳麻")
    private String nickname;

    /**
     * 性别：1-男，2-女
     */
    @ApiModelProperty(value = "性别", example = "1")
    @EnumValid(enumTypeClass = SexEnum.class, allowNull = true, message = "性别有误")
    private Integer sex;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", example = "1999-12-31")
    private Date birthday;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码", example = "18552455")
    @Pattern(regexp = "^[0-9]*$", message = "电话号码格式有误")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "jsa447@163.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "用户id", example = "2")
    @NotNull(message = "用户id不能为空")
    @Min(value = 1, message = "用户id格式错误")
    private Long uid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
