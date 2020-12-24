package utils.http;

import java.util.Map;

/**
 * @author bz
 * @date 2020/12/24
 */
public class HttpRequest {
    private static final int CONNECTION_REQUEST_TIMEOUT = 3000;
    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 30000;
    private String uri;
    private Map<String, Object> queryParams;
    private Object queryObject;
    private Map<String, Object> bodyParams;
    private Object bodyObject;
    private Map<String, Object> headers;
    private int connectionRequestTimeout;
    private int connectTimeout;
    private int socketTimeout;

    public HttpRequest() {
        this.connectionRequestTimeout = 3000;
        this.connectTimeout = 3000;
        this.socketTimeout = 30000;
    }

    public HttpRequest createQueryRequestByMap(String uri, Map<String, Object> queryParams) {
        return this.createQueryRequestByMap(uri, queryParams, (Map)null);
    }

    public HttpRequest createQueryRequestByMap(String uri, Map<String, Object> queryParams, Map<String, Object> headers) {
        this.uri = uri;
        this.queryParams = queryParams;
        this.headers = headers;
        return this;
    }

    public HttpRequest createQueryRequestByObject(String uri, Object queryObject) {
        return this.createQueryRequestByObject(uri, queryObject, (Map)null);
    }

    public HttpRequest createQueryRequestByObject(String uri, Object queryObject, Map<String, Object> headers) {
        this.uri = uri;
        this.queryObject = queryObject;
        this.headers = headers;
        return this;
    }

    public HttpRequest createBodyRequestByMap(String uri, Map<String, Object> bodyParams) {
        return this.createBodyRequestByMap(uri, this.queryParams, (Map)null);
    }

    public HttpRequest createBodyRequestByMap(String uri, Map<String, Object> bodyParams, Map<String, Object> headers) {
        this.uri = uri;
        this.bodyParams = bodyParams;
        this.headers = headers;
        return this;
    }

    public HttpRequest createBodyRequestByObject(String uri, Object bodyObject) {
        return this.createBodyRequestByObject(uri, bodyObject, (Map)null);
    }

    public HttpRequest createBodyRequestByObject(String uri, Object bodyObject, Map<String, Object> headers) {
        this.uri = uri;
        this.bodyObject = bodyObject;
        this.headers = headers;
        return this;
    }

    private HttpRequest(String uri, Map<String, Object> queryParams, Object queryObject, Map<String, Object> bodyParams, Object bodyObject, Map<String, Object> headers, int connectionRequestTimeout, int connectTimeout, int socketTimeout) {
        this.uri = uri;
        this.queryParams = queryParams;
        this.queryObject = queryObject;
        this.bodyParams = bodyParams;
        this.bodyObject = bodyObject;
        this.headers = headers;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    public static HttpRequest.Builder custom() {
        return new HttpRequest.Builder();
    }

    public String getUri() {
        return this.uri;
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }

    public Object getQueryObject() {
        return this.queryObject;
    }

    public Map<String, Object> getBodyParams() {
        return this.bodyParams;
    }

    public Object getBodyObject() {
        return this.bodyObject;
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }

    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public static class Builder {
        private String uri;
        private Map<String, Object> queryParams;
        private Object queryObject;
        private Map<String, Object> bodyParams;
        private Object bodyObject;
        private Map<String, Object> headers;
        private int connectionRequestTimeout = 3000;
        private int connectTimeout = 3000;
        private int socketTimeout = 30000;

        Builder() {
            this.connectionRequestTimeout = 3000;
            this.connectTimeout = 3000;
            this.socketTimeout = 30000;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setQueryParams(Map<String, Object> queryParams) {
            this.queryParams = queryParams;
        }

        public void setQueryObject(Object queryObject) {
            this.queryObject = queryObject;
        }

        public void setBodyParams(Map<String, Object> bodyParams) {
            this.bodyParams = bodyParams;
        }

        public void setBodyObject(Object bodyObject) {
            this.bodyObject = bodyObject;
        }

        public void setHeaders(Map<String, Object> headers) {
            this.headers = headers;
        }

        public void setConnectionRequestTimeout(int connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public HttpRequest build() {
            return new HttpRequest(this.uri, this.queryParams, this.queryObject, this.bodyParams, this.bodyObject, this.headers, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout);
        }
    }
}
