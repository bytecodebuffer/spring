package top.model.query;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author bz
 * @date 2020/9/19
 */
public class RoleQuery {

    @NotNull(message = "roleId 不能为空")
    private Integer roleId;

    @Null
    private String rolename;

    @Override
    public String toString() {
        return "RoleQuery{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
