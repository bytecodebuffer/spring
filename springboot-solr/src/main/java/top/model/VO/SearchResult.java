package top.model.VO;

import lombok.Data;
import top.model.entity.User;

import java.util.List;


/**
 * @author bz
 * @date 2020/10/8
 */
@Data
public class SearchResult {
    private Long total;
    private List<User> userList;
}
