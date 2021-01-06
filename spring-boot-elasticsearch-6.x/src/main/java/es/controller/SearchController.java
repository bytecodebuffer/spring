package es.controller;

import es.entity.TradingAccount;
import es.repository.TradingAccountRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author bz
 * @date 2021/1/6
 */
@RestController
public class SearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private TradingAccountRepository tradingAccountRepository;



    @GetMapping("/search")
    public List<TradingAccount> search(String keyword){
        QueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("username","*"+keyword+"*"))
                .should(QueryBuilders.matchQuery("username",keyword))
                .should(QueryBuilders.wildcardQuery("accountNum","*"+keyword+"*"))
                .should(QueryBuilders.wildcardQuery("fxCode","*"+keyword+"*"));
        Page<TradingAccount> page =  tradingAccountRepository.search(boolQueryBuilder,PageRequest.of(0,7));
        return TradingAccount.exchange(page);
    }

    @GetMapping("/clone")
    public void clone2es(){
        tradingAccountRepository.deleteAll();
        List<TradingAccount> tradingAccountList = Arrays.asList(
                  new TradingAccount("1","1001","7894",1001L,"1452","1452","zhang san"),
                  new TradingAccount("2","1002","4578",1002L,"1452","1452","张阿森纳"),
                  new TradingAccount("3","1003","4542",1003L,"1452","1452","上善若水"),
                  new TradingAccount("4","1004","4547",1004L,"1452","1452","上善上述"),
                  new TradingAccount("5","10015","4578",1005L,"1452","1452","闪闪来u从")
        );
        tradingAccountRepository.saveAll(tradingAccountList);
    }

}
