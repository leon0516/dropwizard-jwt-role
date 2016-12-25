package pub.sharecode.basicapi;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pub.sharecode.basicapi.auth.jwt.JwtAuthFilter;
import pub.sharecode.basicapi.auth.ExampleAuthenticator;
import pub.sharecode.basicapi.auth.ExampleAuthorizer;
import pub.sharecode.basicapi.core.AuthUser;
import pub.sharecode.basicapi.core.Role;
import pub.sharecode.basicapi.core.User;
import pub.sharecode.basicapi.db.UserDao;
import pub.sharecode.basicapi.resources.UserResource;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: BasicApiApplication.java
 * @Package: pub.sharecode.basicapi
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 21:00
 */
public class BasicApiApplication extends Application<BasicApiConfiguration> {
    public static void main(String[] args) throws Exception {
        new BasicApiApplication().run(args);
    }

    public void run(BasicApiConfiguration basicApiConfiguration, Environment environment) throws Exception {
        final byte[] key = basicApiConfiguration.getJwtTokenSecret();
        UserDao userDao = new UserDao(hibernate.getSessionFactory());
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance
        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<AuthUser>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new ExampleAuthenticator())
                        .setAuthorizer(new ExampleAuthorizer())
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
//        environment.jersey().register(new JwtResource(basicApiConfiguration.getJwtTokenSecret()));
        environment.jersey().register(new UserResource(userDao, basicApiConfiguration.getJwtTokenSecret()));

    }

    private final HibernateBundle<BasicApiConfiguration> hibernate = new HibernateBundle<BasicApiConfiguration>(
            User.class, Role.class//TODO 需要注册 entity类,千万不要忘记
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(BasicApiConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<BasicApiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
}
