package pub.sharecode.basicapi;


import com.fasterxml.jackson.annotation.JsonProperty;

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
