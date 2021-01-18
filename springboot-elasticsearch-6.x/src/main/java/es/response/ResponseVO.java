package es.response;

import es.entity.TradingAccount;
import lombok.Data;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.List;
import java.util.Map;

/**
 * @author bz
 * @date 2021/1/14
 */
@Data
public class ResponseVO {

    private List<TradingAccount> tradingAccountList;
}
