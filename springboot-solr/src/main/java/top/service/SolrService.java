package top.service;

import top.model.VO.SearchResult;
import top.model.entity.User;

import java.util.List;

/**
 * @author bz
 * @date 2020/10/8
 */
public interface SolrService {

    /**
     * 初始新增信息
     * @return
     * @throws Exception
     */
    List<User> saveUser() throws Exception;

    /**
     * 查询信息，默认不分页
     * @param queryString
     * @return
     * @throws Exception
     */
    SearchResult searchUsers(String queryString) throws Exception;

    /**
     * 根据关键词查询名称
     * @return
     * @throws Exception
     * @param name
     */
    List<String> searchName(String name)throws Exception;

}
