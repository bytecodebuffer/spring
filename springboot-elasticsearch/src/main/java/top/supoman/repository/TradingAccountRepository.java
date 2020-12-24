package top.supoman.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import top.supoman.model.entity.TradingAccount;

/**
 * @author bz
 * @date 2020/10/15
 */
@Component
public interface TradingAccountRepository extends ElasticsearchRepository<TradingAccount,Long> {
}
