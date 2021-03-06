package es.entity;


import lombok.Data;
import java.util.Map;

/**
 * @author bz
 * @date 2021/1/6
 */
@Data
public class TradingAccount {

    private String id;

    private String userId;

    private String fxCode;

    private Long accountId;

    private String accountNum;

    private String avatar;

    private String username;

    private Map<String, String> highLightMap;

}
