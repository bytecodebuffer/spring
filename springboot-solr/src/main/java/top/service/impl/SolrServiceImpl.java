package top.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.noggit.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.model.VO.SearchResult;
import top.model.entity.User;
import top.service.SolrService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author bz
 * @date 2020/10/8
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private SolrClient solrClient;
    private static final Integer initSize = 10;


    @Override
    public List<User> saveUser() throws Exception{
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <initSize ; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("name"+i);
            user.setSex("男"+i);
            user.setAddress("第"+i+"街道".toString());
            user.setHost(1100L);
            userList.add(user);
        }
        solrClient.addBeans(userList);
        solrClient.commit();
        return userList;
    }

    @Override
    public SearchResult searchUsers(String queryString) throws Exception{
        queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryString);
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        solrQuery.setHighlight(true);
        //设置高亮显示的域
        solrQuery.addHighlightField("id");
        //高亮显示前缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        //后缀
        solrQuery.setHighlightSimplePost("</font>");
        //设置默认搜索域
        solrQuery.set("df", "id");

        SearchResult result = searchItemDao(solrQuery);
        return result;
    }


    public SearchResult searchItemDao(SolrQuery solrQuery) throws Exception {
        SearchResult searchResult = new SearchResult();
        List<User> userList = new ArrayList<User>();
        //执行查询
        QueryResponse response = solrClient.query(solrQuery);
        if(response==null){
            return searchResult;
        }
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        if(solrDocumentList.isEmpty()){
            return searchResult;
        }
        //获取高亮
        Map<String, Map<String, List<String>>> map = response.getHighlighting();
        searchResult.setTotal(solrDocumentList.getNumFound());

        for(SolrDocument document: solrDocumentList) {
            User user = new User();
            List<String> list = map.get(document.get("id")).get("id");
            System.out.println(document.get("id"));
            System.out.println(map.get(document.get("id")));
            if(!CollectionUtils.isEmpty(list)){
                document.setField("highLight",list.get(0));
            }
            String userStr = JSONUtil.toJSON(document);
            user = JSON.parseObject(userStr,User.class);

            userList.add(user);
        }

        searchResult.setUserList(userList);
        return searchResult;
    }



    @Override
    public List<String> searchName(String name) throws Exception {
        List<String> results = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(name);
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font style='color:red'>");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.set("df","name");
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        QueryResponse response = solrClient.query(solrQuery);
        if(response==null){
            return null;
        }
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        if(solrDocumentList.isEmpty()){
            return null;
        }
        //获取高亮
        Map<String, Map<String, List<String>>> map = response.getHighlighting();
        for(SolrDocument document : solrDocumentList){
            System.out.println(document.get("id"));
            System.out.println(map.get(document.get("id")));
            List<String> list = map.get(document.get("id")).get("name");
            if(!CollectionUtils.isEmpty(list)){
                results.add(list.get(0));
            }
        }
        return results;
    }


}
