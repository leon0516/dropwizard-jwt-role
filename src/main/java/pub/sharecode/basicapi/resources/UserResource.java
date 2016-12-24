package pub.sharecode.basicapi.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.hibernate.UnitOfWork;
import pub.sharecode.basicapi.core.User;
import pub.sharecode.basicapi.db.UserDao;

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

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
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
}
