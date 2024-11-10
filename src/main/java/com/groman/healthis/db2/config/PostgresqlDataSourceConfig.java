package com.groman.healthis.db2.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;  // Updated import
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.groman.healthis.db2.repository",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
public class PostgresqlDataSourceConfig {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("postgresqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.groman.healthis.db1.domain")
                .persistenceUnit("postgresql")
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
