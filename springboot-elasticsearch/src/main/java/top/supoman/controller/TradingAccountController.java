package top.supoman.controller;


import org.elasticsearch.client.RestClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.supoman.model.entity.TradingAccount;
import top.supoman.model.result.ResultVO;
import top.supoman.repository.TradingAccountRepository;


/**
 * @author bz
 * @date 2020/10/15
 */
@RestController
@RequestMapping("/api")
public class TradingAccountController {

    /**
     * search() 弃用
     */
    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private RestClient restClient;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    /**
     * 查询所有的数据
     * @return
     * @throws Exception
     */
    @GetMapping("/searchAll")
    public ResultVO searchAll() throws Exception{
        SearchHits<TradingAccount> hits = elasticsearchRestTemplate.search(Query.findAll(), TradingAccount.class, IndexCoordinates.of("trading_account"));

        return  ResultVO.succcess(hits);

    }

    /**
     * 根据关键字查询
     * @return
     * @throws Exception
     */
    @GetMapping("/search")
    public ResultVO searchByKey(@RequestParam("keyword")String keyword) throws Exception{
        String param = "*"+keyword+"*";
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .should(QueryBuilders.wildcardQuery("userId",param))
                .should(QueryBuilders.wildcardQuery("username",param))
                .should(QueryBuilders.wildcardQuery("accountNum",param));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("username");
        highlightBuilder.field("userId");
        highlightBuilder.field("accountNum");
        highlightBuilder.preTags("<font color:red>");
        highlightBuilder.postTags("</font>");
        HighlightQuery highlightQuery = new HighlightQuery(highlightBuilder);

        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQueryBuilder);
        nativeSearchQuery.setPageable(PageRequest.of(0,2));
        nativeSearchQuery.setHighlightQuery(highlightQuery);
        SearchHits<TradingAccount> hits = elasticsearchRestTemplate.search(nativeSearchQuery, TradingAccount.class, IndexCoordinates.of("trading_account"));
        return  ResultVO.succcess(hits);
    }





}
