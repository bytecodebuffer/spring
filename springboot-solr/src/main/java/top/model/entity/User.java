package top.model.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @author bz
 * @date 2020/10/8
 */
@Data
public class User  implements Serializable {

    private static final long serialVersionUID = -1755311179877626351L;

    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("sex")
    private String sex;

    @Field("address")
    private String address;

    @Field("host")
    private Long host;

    private String highLight;
}
