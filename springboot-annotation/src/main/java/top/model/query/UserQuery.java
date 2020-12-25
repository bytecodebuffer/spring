package top.model.query;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author bz
 * @date 2020/9/19
 */
public class UserQuery {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "邮件不能为空")
    private String email;

    @NotNull(message = "用户id 不能为空")
    private Integer uid;

    @Override
    public String toString() {
        return "UserQuery{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uid=" + uid +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
