package pub.sharecode.basicapi.db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import pub.sharecode.basicapi.core.User;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: UserDao.java
 * @Package: pub.sharecode.basicapi.db
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 21:53
 */
public class UserDao extends AbstractDAO<User> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<User> getById(long id) {
        return Optional.ofNullable(get(id));
    }

    public List<User> getAllUser() {
        return list(namedQuery("pub.sharecode.basicapi.core.User.findAll"));
    }

    public User createUser(User u) {
        return persist(u);
    }
}
