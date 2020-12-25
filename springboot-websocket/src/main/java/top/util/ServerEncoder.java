package top.util;

import com.alibaba.fastjson.JSON;
import top.model.Vo.MessageVO;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author bz
 * @date 2020/9/17
 * 擦模:
 * https://my.oschina.net/u/2450666/blog/784984?utm_medium=referral
 */
public class ServerEncoder implements Encoder.Text<MessageVO>{
    @Override
    public String encode(MessageVO messageVO) throws EncodeException {
        try{
            return JSON.toJSONString(messageVO);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
