package es.repository;

import es.entity.TradingAccount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bz
 * @date 2021/1/6
 */
@Repository
public interface TradingAccountRepository extends ElasticsearchRepository<TradingAccount, String> {
}
