package es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bz
 * @date 2021/1/6
 */

@Data
@Document(indexName = "trading_account",type = "doc")
public class TradingAccount {

    @Id
    private String id;

    @Field
    private String userId;

    private String fxCode;

    private Long accountId;

    private String accountNum;

    private String avatar;

    private String username;



    public TradingAccount() {
    }

    public TradingAccount(String id, String userId, String fxCode, Long accountId, String accountNum, String avatar, String username) {
        this.id = id;
        this.userId = userId;
        this.fxCode = fxCode;
        this.accountId = accountId;
        this.accountNum = accountNum;
        this.avatar = avatar;
        this.username = username;
    }

    public static List<TradingAccount> exchange(Page<TradingAccount> page){
        List<TradingAccount> tradingAccountList = new ArrayList<>();
        for(TradingAccount account: page){
            tradingAccountList.add(account);
        }
        return tradingAccountList;
    }
}
