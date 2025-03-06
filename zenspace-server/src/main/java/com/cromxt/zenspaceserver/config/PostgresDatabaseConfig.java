package com.cromxt.zenspaceserver.config;


import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@Configuration
public class PostgresDatabaseConfig extends AbstractR2dbcConfiguration {

    private final String host;
    private final Integer port;
    private final String database;
    private final String username;
    private final String password;

    public PostgresDatabaseConfig(Environment environment) {
        String hostName = environment.getProperty("ZENSPACE_CONFIG.POSTGRES.HOST");
        Integer port = environment.getProperty("ZENSPACE_CONFIG.POSTGRES.PORT",Integer.class);
        String database = environment.getProperty("ZENSPACE_CONFIG.POSTGRES.DATABASE");
        String username = environment.getProperty("ZENSPACE_CONFIG.POSTGRES.USERNAME");
        String password = environment.getProperty("ZENSPACE_CONFIG.POSTGRES.PASSWORD");

        if (hostName != null && port != null && database != null && username != null && password != null) {
            this.host = hostName;
            this.port = port;
            this.database = database;
            this.username = username;
            this.password = password;
        }else{
            throw new RuntimeException("Database configuration not found");
        }
    }

    @Bean
    @Override
    @NonNull
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .username(username)
                .password(password)
                .host(host)
                .port(port)
                .database(database)
                .build());
    }
}
