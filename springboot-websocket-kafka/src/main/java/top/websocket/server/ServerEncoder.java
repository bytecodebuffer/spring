package top.websocket.server;

import com.alibaba.fastjson.JSON;
import top.websocket.vo.MessageObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author bz
 * @date 2020/9/21
 */
public class ServerEncoder implements Encoder.Text<MessageObject>{
    @Override
    public String encode(MessageObject messageVO) throws EncodeException {
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
