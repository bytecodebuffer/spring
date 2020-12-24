package supoman.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/9
 */
@Mapper
public interface UserDao {

    /**
     * 更新用户的年龄
     * @param age
     * @param id
     */
    void updateUserAge(@Param("id")Long id,@Param("age") Integer age);

    /**
     * 获取规则字符串
     * @param id
     * @return
     */
    String getUserCronStr(@Param("id")Long id);


    /**
     * 获取用户名称
     * @return
     */
    List<String> getUserName1();
}
