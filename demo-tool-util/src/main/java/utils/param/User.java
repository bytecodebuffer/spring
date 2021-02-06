package utils.param;

import lombok.Data;

/**
 * @author bz
 * @date 2021/2/6
 */
@Data
public class User {

    private String username;

    private Integer age;

    public User(String name){
        this.username = name;
    }
}
