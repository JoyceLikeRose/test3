package cn.beecp.starter.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDs",
        transactionManagerRef = "transactionManagerDs",
        basePackages = {"cn.beecp.starter.demo.entity"})
public class DsJpaConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSource ds;

    @Primary
    @Bean(name = "transactionManagerDs")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

    @Primary
    @Bean(name = "entityManagerDs")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryDs")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ds)
                .properties(getVendorProperties(ds))
                .packages("cn.beecp.starter.demo.entity")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String,Object> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
}
