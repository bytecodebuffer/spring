package elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import elasticsearch.model.Address;
import org.springframework.stereotype.Repository;

/**
 * @author bz
 * @date 2020/10/15
 */
@Repository
public interface AddressRepository extends ElasticsearchRepository<Address,Long> {
}
