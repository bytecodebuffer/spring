package top.supoman.model.result;

import lombok.Data;
import top.supoman.model.entity.TradingAccount;

import java.util.List;
import java.util.Map;

/**
 * @author bz
 * @date 2020/11/10
 */
@Data
public class TradingAccountVO {
    private TradingAccount tradingAccount;
    private Map<String, List<String>> highLight;
}
