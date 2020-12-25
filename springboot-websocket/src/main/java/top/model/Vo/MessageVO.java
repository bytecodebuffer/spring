package top.model.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bz
 * @date 2020/9/17
 */
@Data
public class MessageVO implements Serializable {

    private String seq;
    private String data;
    private String userId;
}
