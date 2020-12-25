package top.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author lgs
 */
@ApiModel(description = "用户实体类")
public class UserEntity {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", example = "2")
    private Long uid;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", example = "壳麻")
    private String nickname;

    /**
     * 性别：1-男，2-女
     */
    @ApiModelProperty(value = "性别", example = "1")
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
    private String email;

    /**
     * 来源客户端
     */
    @ApiModelProperty(value = "来源客户端", example = "2")
    private Integer sourceClient;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "2020-02-13 17:37:29", hidden = true)
    @JsonIgnore
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", example = "2020-02-13 17:37:29", hidden = true)
    @JsonIgnore
    private Date updateTime;

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

    public Integer getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(Integer sourceClient) {
        this.sourceClient = sourceClient;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
