package es.controller;



import es.entity.TradingAccount;
import es.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author bz
 * @date 2021/1/6
 */
@Slf4j
@RestController
@AllArgsConstructor
public class SearchController {


    private final RestHighLevelClient client;

    private static final String index = "trading_account";

    private static final String type = "doc";

    @GetMapping("/search")
    public List<TradingAccount> search(String keyword) throws Exception{

        SearchRequest searchRequest = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.should(QueryBuilders.matchQuery("username",keyword))
                .should(QueryBuilders.wildcardQuery("username","*"+keyword.toLowerCase()+"*"))
                .should(QueryBuilders.wildcardQuery("fxCode","*"+keyword.toLowerCase()+"*")).
                 should(QueryBuilders.wildcardQuery("accountNum","*"+keyword.toLowerCase()+"*"));;
        //分页-排序
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(3);
        searchSourceBuilder.sort("createTime");
        searchSourceBuilder.query(boolQuery);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("username");
        highlightBuilder.field("fxCode");
        highlightBuilder.field("accountNum");
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits searchHits = response.getHits();
        List<TradingAccount> tradingAccountList = new ArrayList<>();
        if(searchHits!=null){
            SearchHit[] searchHitList = searchHits.getHits();
            for(SearchHit item : searchHitList){
                Map<String, String> hitMap  = getHighLightMap(item);
                TradingAccount tradingAccount = JsonUtils.json2obj(item.getSourceAsString(), TradingAccount.class);
                assert tradingAccount != null;
                tradingAccount.setId(item.getId());
                tradingAccount.setHighLightMap(hitMap);
                tradingAccountList.add(tradingAccount);
            }
        }
        return tradingAccountList;
    }


    public Map<String, String> getHighLightMap(SearchHit searchHit){
        String field = searchHit.getHighlightFields().keySet().iterator().next();
        Map<String, HighlightField> highlightFieldMap =  searchHit.getHighlightFields();
        Text[] texts = highlightFieldMap.get(field).fragments();
        Map<String, String> map = new HashMap<>(4);
        map.put(field,texts[0].toString());
        return map;
    }

    /**
     * 批量删除
     * @throws Exception
     */
    @GetMapping("/delete")
    public void delete() throws Exception{
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest();
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
        deleteByQueryRequest.indices(index);
        client.deleteByQuery(deleteByQueryRequest,RequestOptions.DEFAULT);
    }

    /**
     * 保存数据
     */
    @GetMapping("/save")
    public void save() throws Exception{
        TradingAccount tradingAccount = new TradingAccount();
        tradingAccount.setAccountId(100L);

        IndexRequest request = new IndexRequest("post");
        request.index(index);
        request.type(type);

        request.source(JsonUtils.obj2Map(tradingAccount), XContentType.JSON);
        IndexResponse response = client.index(request,RequestOptions.DEFAULT);

        System.out.println(response);

    }

    public void clone2Elasticsearch(){


    }



}
