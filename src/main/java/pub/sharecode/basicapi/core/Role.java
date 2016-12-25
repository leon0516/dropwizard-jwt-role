package pub.sharecode.basicapi.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: Role.java
 * @Package: pub.sharecode.basicapi.db
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 23:22
 */
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private long roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;

    public Role() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
