package elasticsearch.controller;


import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import elasticsearch.model.Address;
import elasticsearch.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author bz
 * @date 2020/10/15
 */
@AllArgsConstructor
@Controller
public class SearchController {

    private final AddressRepository addressRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 跳转URL
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "/index";
    }

    /**
     * 根据关键字查询
     * @return
     * @throws Exception
     */
    @GetMapping("/search")
    @ResponseBody
    public List<Address> searchByKey(@RequestParam("keyword")String keyword) throws Exception{

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .should(QueryBuilders.wildcardQuery("name","*"+keyword+"*"))
                .should(QueryBuilders.matchQuery("name",keyword))
                .should(QueryBuilders.wildcardQuery("fullName","*"+keyword+"*"))
                .should(QueryBuilders.matchQuery("fullName",keyword));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQueryBuilder);
        nativeSearchQuery.setPageable(PageRequest.of(0,10));
        SearchHits<Address> addressSearchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Address.class, IndexCoordinates.of("address"));
        List<Address> addresses = new ArrayList<>();
        if(addressSearchHits!=null && addressSearchHits.hasSearchHits()){
            addresses = addressSearchHits.stream()
                    .map(item->item.getContent())
                    .collect(Collectors.toList());
        }
        return addresses;
    }





}
