package pub.sharecode.basicapi.auth;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import java.util.Optional;

import io.dropwizard.auth.Authenticator;
import pub.sharecode.basicapi.core.AuthUser;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: ExampleAuthenticator.java
 * @Package: pub.sharecode.basicapi.auth.test
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-25 12:42
 */
public class ExampleAuthenticator implements Authenticator<JwtContext, AuthUser> {

    public ExampleAuthenticator() {
    }

    @Override
    public Optional<AuthUser> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

        try {
            final String id = context.getJwtClaims().getStringClaimValue("id");
            Optional<AuthUser> user = Optional.of(new AuthUser());
            user.get().setName(context.getJwtClaims().getStringClaimValue("name"));
            user.get().setId(Long.valueOf(id));
            user.get().setRole(context.getJwtClaims().getStringClaimValue("role"));
            return user;
        } catch (MalformedClaimException e) {
            return Optional.empty();
        }
    }
}