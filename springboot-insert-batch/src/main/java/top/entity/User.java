package top.entity;

import top.utils.RandomName;
import lombok.Data;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author bz
 * @date 2020/10/20
 */
@Data
public class User {
    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String address;
    private Integer age;
    private String email;
    private Date createTime;
    private Date updateTime;

    public User() {
        this.userId = UUID.randomUUID().toString().replaceAll("-","");
        this.userName = RandomName.randomName(true,3);
        this.password = "123456";
        this.address = "中国";
        this.age = (new Random().nextInt(100))+1;
        this.email = this.userName+"@163.com";
    }
}
