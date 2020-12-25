package top.model.param;

import top.enums.types.ClientTypeEnum;
import top.enums.types.SexEnum;
import top.validator.protocol.EnumValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 新增用户参数类
 *
 * @author lgs
 */
@ApiModel(description = "新增用户参数类")
public class AddUserParam {

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", example = "壳麻")
    @NotBlank(message = "昵称不能为空")
    @Size(max = 30, message = "昵称长度不符合")
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
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "jsa447@163.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 来源客户端
     */
    @ApiModelProperty(value = "来源客户端", example = "2")
    @EnumValid(enumTypeClass = ClientTypeEnum.class, message = "来源客户端有误")
    private Integer sourceClient;

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

    public Integer getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(Integer sourceClient) {
        this.sourceClient = sourceClient;
    }

}
