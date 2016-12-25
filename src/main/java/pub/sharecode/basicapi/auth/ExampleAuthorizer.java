package pub.sharecode.basicapi.auth;

import io.dropwizard.auth.Authorizer;
import pub.sharecode.basicapi.core.AuthUser;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: ExampleAuthorizer.java
 * @Package: pub.sharecode.basicapi.auth.test
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-25 12:55
 */
public class ExampleAuthorizer implements Authorizer<AuthUser> {
    @Override
    public boolean authorize(AuthUser user, String role) {
        return role.equals(user.getRole());
    }
}