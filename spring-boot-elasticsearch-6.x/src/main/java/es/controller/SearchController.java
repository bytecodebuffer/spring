package es.controller;



import es.entity.TradingAccount;
import es.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @GetMapping("/search")
    public  List<TradingAccount> search(String keyword) throws Exception{

        SearchRequest searchRequest = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.should(QueryBuilders.termQuery("username",keyword));
        //分页-排序
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(3);
        searchSourceBuilder.sort("createTime");
        searchSourceBuilder.query(boolQuery);

        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);

        SearchHits searchHits = response.getHits();

        List<TradingAccount> tradingAccountList = new ArrayList<>();
        if(searchHits!=null){
            SearchHit[] searchHitList = searchHits.getHits();
            for(SearchHit item : searchHitList){
                TradingAccount tradingAccount = JsonUtils.json2obj(item.getSourceAsString(), TradingAccount.class);
                tradingAccount.setId(item.getId());
                tradingAccountList.add(tradingAccount);
            }
        }
        return tradingAccountList;
    }

    @GetMapping("/delete")
    public void delete(){

    }

    @GetMapping("/save")
    public void save(){

    }

    public void clone2Elasticsearch(){

    }



}
