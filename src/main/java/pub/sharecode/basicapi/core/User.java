package pub.sharecode.basicapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: User.java
 * @Package: pub.sharecode.basicapi.core
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 21:51
 */
@Entity
@Table(name = "USER")
@NamedQueries(
        {
                @NamedQuery(
                        name = "pub.sharecode.basicapi.core.User.findAll",
                        query = "SELECT s FROM User s"
                )
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @JsonProperty
    @Column(name = "NAME")
    private String name;
    @JsonProperty
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
