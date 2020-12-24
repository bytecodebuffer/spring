package top.supoman.model.result;

import lombok.Data;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import top.supoman.model.entity.TradingAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bz
 * @date 2020/11/10
 */
@Data
public class ResultVO{
    private  List<TradingAccountVO> data = new ArrayList<>();
    private  Long total;
    private  String status;


    public ResultVO(SearchHits<TradingAccount> hits){
        this.status = "200";
        this.total = hits.getTotalHits();
        for(SearchHit<TradingAccount> e:hits){
            TradingAccountVO tradingAccountVO = new TradingAccountVO();
            tradingAccountVO.setTradingAccount(e.getContent());
            tradingAccountVO.setHighLight(e.getHighlightFields());
            data.add(tradingAccountVO);
        }
    }
    public static ResultVO succcess(SearchHits<TradingAccount> hits){
        return new ResultVO(hits);
    }


}
