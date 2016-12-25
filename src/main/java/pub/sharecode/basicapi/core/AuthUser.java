package pub.sharecode.basicapi.core;

import java.security.Principal;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: AuthUser.java
 * @Package: pub.sharecode.basicapi.core
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-25 12:44
 */
public class AuthUser implements Principal {
    private String name;
    private String role;
    private long id;

    public AuthUser() {
    }

    public AuthUser(long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }
}
