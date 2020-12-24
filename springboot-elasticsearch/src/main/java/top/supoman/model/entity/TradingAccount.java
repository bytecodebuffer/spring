package top.supoman.model.entity;


import lombok.Data;

/**
 * @author bz
 * @date 2020/10/15
 * indexName 必须小写
 */
@Data
public class TradingAccount {

    private String id;

    private String userId;

    private Long accountId;

    private String accountNum;

    private String avatar;

    private String username;

    private Integer serialNumber;

    private Long brokerId;

    private String brokerName;

    private String brokerIconUrl;

}
