package com.axolotl;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private final DataSourceProperties dataSourceProperties;

    public AppConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource realDataSource() {
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return new DataSourceSpy(realDataSource());
    }
}
