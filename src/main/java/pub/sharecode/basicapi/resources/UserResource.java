package pub.sharecode.basicapi.resources;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import pub.sharecode.basicapi.core.AuthUser;
import pub.sharecode.basicapi.core.Role;
import pub.sharecode.basicapi.core.Token;
import pub.sharecode.basicapi.core.User;
import pub.sharecode.basicapi.db.UserDao;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: UserResource.java
 * @Package: pub.sharecode.basicapi.resources
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 21:55
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDao userDao;
    private byte[] key;

    public UserResource(UserDao userDao, byte[] key) {
        this.userDao = userDao;
        this.key = key;
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    public Optional<User> getById(@PathParam("id") Long id) {
        return userDao.getById(id);
    }

    @GET
    @UnitOfWork
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @GET
    @Path("/login")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Token> login(@QueryParam("id") Long id, @QueryParam("password") String password) {
        Optional<User> user = userDao.getById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("NO this user");
        }
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(20);
        claims.setSubject("token");
        claims.setStringClaim("id", String.valueOf(user.get().getId()));
        claims.setStringClaim("name", String.valueOf(user.get().getName()));
        claims.setStringClaim("role", String.valueOf(user.get().getRole().getRoleName()));
        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setDoKeyValidation(false);
        jws.setKey(new HmacKey(key));
        try {
            return Optional.of(new Token(jws.getCompactSerialization()));
        } catch (JoseException e) {
            throw Throwables.propagate(e);
        }
    }

    @GET
    @Path("/check-token")
    @UnitOfWork
    @RolesAllowed("SUPER_USER")
    public Map<String, Object> get(@Auth AuthUser user) {
        return ImmutableMap.<String, Object>of("username", user.getName(), "id", user.getId(), "role", user.getRole());
    }

    @GET
    @Path("/check-test")
    @UnitOfWork
    @RolesAllowed("SUPER_USER")
    public Map<String, Object> gettest() {
        return ImmutableMap.<String, Object>of("ok", true);
    }

    @GET
    @Path("register")
    @UnitOfWork
    public User register(@QueryParam("name") String name, @QueryParam("password") String password) {
        Role r = new Role();
        r.setRoleId(1);
        r.setRoleName("SUPER_USER");
        User user = new User(name, password, r);
        return userDao.createUser(user);
    }
}
