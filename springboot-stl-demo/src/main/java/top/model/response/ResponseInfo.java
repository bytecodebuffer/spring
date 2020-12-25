package top.model.response;

import top.enums.SubCodeEnum;
import top.enums.subcodes.SystemSubCode;
import top.util.JsonUtils;
import top.util.SubCodeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 标准返回类型
 *
 * @author lgs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class ResponseInfo<T> {

    /****************************请求编码********************************************/
    /**
     * 程序正常
     */
    public static final Integer RESPONSE_SUCCESS = 0;
    /**
     * 程序异常
     */
    public static final Integer RESPONSE_FAILURE = -1;
    /**
     * 默认返回的提示消息
     */
    private static final String DEFAULT_OK_MESSAGE = "ok";

    /**
     * 请求正常时，code为0，异常为-1
     */
    private Integer code = 0;

    /**
     * 业务描述码，第1位表示相应的环境，第2～3位表示域名编号，第4～6位表示API编号，第7～8位表示业务描述
     */
    private String subCode = "00000000";

    /**
     * 请求结果的业务描述,成功时不设置
     */
    private String message;
    /**
     * 请求的响应数据
     */
    private T bodyMessage;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(T bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", subCode='" + subCode + '\'' +
                ", message='" + message + '\'' +
                ", bodyMessage='" + bodyMessage + '\'' +
                '}';
    }

    public String dropFirstCharactorOfSubcode() {
        int position = 1;
        return subCode.substring(position);
    }

    public static <T> ResponseInfo ok(List<T> result, Object attach, int total, int index, int size, SubCodeEnum subCodeEnum) {
        String fullSubCode = fullSubCode(subCodeEnum);
        ResponseInfo responseInfo = new ResponseInfo();
        PageModel<T> pageModel = new PageModel();
        pageModel.setData(result);
        pageModel.setTotal(total);
        pageModel.setAttach(attach);
        pageModel.setIndex(index);
        pageModel.setSize(size);
        if (StringUtils.isEmpty(responseInfo.message)) {
            responseInfo.setMessage(DEFAULT_OK_MESSAGE);
        }
        responseInfo.code = RESPONSE_SUCCESS;
        responseInfo.bodyMessage = pageModel;
        responseInfo.subCode = fullSubCode;
        return responseInfo;
    }

    public static <T> ResponseInfo ok(List<T> result, Object attach, int total, SubCodeEnum subCodeEnum) {
        return ok(result, attach, total, 0, 0, subCodeEnum);
    }

    public static <T> ResponseInfo ok(List<T> result, int total, SubCodeEnum subCodeEnum) {
        return ok(result, null, total, subCodeEnum);
    }

    public static <T> ResponseInfo ok(List<T> result, int total, int index, int size, SubCodeEnum subCodeEnum) {
        return ok(result, null, total, index, size, subCodeEnum);
    }

    public static ResponseInfo ok(String key, Object value, SubCodeEnum subCodeEnum) {
        HashMap<String, Object> result = new HashMap<>(1);
        result.put(key, value);
        return ok(result, subCodeEnum);
    }

    public static <T> ResponseInfo ok(T object, SubCodeEnum subCodeEnum) {
        String json = JsonUtils.toJson(object);
        return ok(json, subCodeEnum);
    }

    public static <T> ResponseInfo ok(T object, String message, SubCodeEnum subCodeEnum) {
        ResponseInfo responseInfo = ok(object, subCodeEnum);
        responseInfo.setMessage(message);
        return responseInfo;
    }

    public static ResponseInfo ok(SubCodeEnum subCodeEnum) {
        return ok(null, subCodeEnum);
    }

    public static ResponseInfo ok(String bodyMessage, SubCodeEnum subCodeEnum) {
        String fullSubCode = fullSubCode(subCodeEnum);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.code = RESPONSE_SUCCESS;
        responseInfo.bodyMessage = bodyMessage;
        responseInfo.subCode = fullSubCode;
        if (StringUtils.isEmpty(responseInfo.message)) {
            responseInfo.setMessage(DEFAULT_OK_MESSAGE);
        }
        return responseInfo;
    }

    public static ResponseInfo info(String message, SubCodeEnum subCodeEnum) {
        String fullSubCode = fullSubCode(subCodeEnum);
        ResponseInfo standardResponse = new ResponseInfo();
        standardResponse.code = RESPONSE_SUCCESS;
        standardResponse.message = message;
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }

    public static ResponseInfo error(String message, SubCodeEnum subCodeEnum) {
        String fullSubCode = fullSubCode(subCodeEnum);
        ResponseInfo standardResponse = new ResponseInfo();
        standardResponse.code = RESPONSE_FAILURE;
        standardResponse.message = message;
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }

    private static String fullSubCode(SubCodeEnum subCodeEnum) {
        String prefix = SubCodeUtils.getEnvPrefixOfSubCode();
        return prefix + subCodeEnum.getSubCode();
    }

    /**
     * 鉴权失败
     *
     * @param msg         失败信息
     * @param subCodeEnum 编码
     * @return
     */
    public static ResponseInfo authError(String msg, SubCodeEnum subCodeEnum) {
        String fullSubCode = fullSubCode(SystemSubCode.AUTHORIZATION_REQUEST_EXCEPTION);
        ResponseInfo standardResponse = new ResponseInfo();
        standardResponse.code = RESPONSE_FAILURE;
        standardResponse.message = msg;
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }

    /**
     * 服务调用异常
     *
     * @return
     */
    public static ResponseInfo serviceError() {
        String fullSubCode = fullSubCode(SystemSubCode.SERVICE_CALL_ERROR);
        ResponseInfo standardResponse = new ResponseInfo();
        standardResponse.code = RESPONSE_FAILURE;
        standardResponse.message = SystemSubCode.SERVICE_CALL_ERROR.getDesc();
        standardResponse.subCode = fullSubCode;
        return standardResponse;
    }
}
