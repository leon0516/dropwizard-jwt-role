package pub.sharecode.basicapi.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: Token.java
 * @Package: pub.sharecode.dropwizard.core
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-17 23:03
 */
public class Token {
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
    }
}
