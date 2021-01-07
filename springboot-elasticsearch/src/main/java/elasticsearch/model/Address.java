package elasticsearch.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author bz
 * @date 2020/10/15
 * indexName 必须小写
 */
@Data
@Document(indexName = "address")
public class Address {

    @Id
    private String id;

    private String name;

    private String fullName;

}
