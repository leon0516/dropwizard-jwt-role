package pub.sharecode.basicapi;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        environment.jersey().register(new UserResource(new UserDao(hibernate.getSessionFactory())));
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
