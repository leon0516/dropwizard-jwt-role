package pub.sharecode.basicapi;


import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * TODO 在此写用途
 *
 * @version V1.0 TODO<描述当前版本功能>
 * @FileName: BasicApiConfiguration.java
 * @Package: pub.sharecode.basicapi
 * @author: leon
 * @email: leon860516@gmail.com
 * @date: 2016-12-24 21:00
 */
public class BasicApiConfiguration extends Configuration {
    @NotEmpty
    private String jwtTokenSecret = "dfwzsdzwh823zebdwdz772632gdsbd";

    @JsonProperty("jwttoken")
    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
