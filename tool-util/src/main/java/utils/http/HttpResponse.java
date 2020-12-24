package utils.http;

/**
 * @author bz
 * @date 2020/12/24
 */
public class HttpResponse {

    private static final int REQUEST_SUCCESS = 0;
    private static final int REQUEST_FAILURE = -1;
    private int code;
    private String subCode;
    private String message = "操作成功";
    private String bodyMessage;

    public HttpResponse() {
    }

    public String toString() {
        return "HttpResponse{code=" + this.code + ", subCode='" + this.subCode + '\'' + ", message='" + this.message + '\'' + ", bodyMessage='" + this.bodyMessage + '\'' + '}';
    }

    public static HttpResponse ok(String subCode, String bodyMessage) {
        HttpResponse response = ok(subCode, bodyMessage, (String)null);
        return response;
    }

    public static HttpResponse ok(String subCode, String bodyMessage, String message) {
        HttpResponse response = new HttpResponse();
        response.code = 0;
        response.subCode = subCode;
        response.bodyMessage = bodyMessage;
        if (null != message) {
            response.message = message;
        }

        return response;
    }

    public static HttpResponse info(String subCode, String message) {
        HttpResponse response = new HttpResponse();
        response.code = 0;
        response.subCode = subCode;
        response.message = message;
        return response;
    }

    public static HttpResponse error(String subCode, String message) {
        HttpResponse response = new HttpResponse();
        response.code = -1;
        response.subCode = subCode;
        response.message = message;
        return response;
    }
}
