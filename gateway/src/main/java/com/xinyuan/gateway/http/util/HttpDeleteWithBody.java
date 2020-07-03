package com.xinyuan.gateway.http.util;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * 没有现成的delete可以带json的，自己实现一个，参考HttpPost的实现
 * Created by pen on 2017/10/24.
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public HttpDeleteWithBody() {
    }

    public HttpDeleteWithBody(URI uri) {
        setURI(uri);
    }

    public HttpDeleteWithBody(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
