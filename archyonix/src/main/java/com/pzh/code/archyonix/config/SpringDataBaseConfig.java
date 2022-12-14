package com.pzh.code.archyonix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "com.pzh.code.archyonix.repository.db1",
        entityManagerFactoryRef = "primaryEntityManager",
        transactionManagerRef = "primaryTransactionManager"
)
public class SpringDataBaseConfig {
        @Autowired
        private Environment env;

        @Bean
        @Primary
        public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
                LocalContainerEntityManagerFactoryBean em
                        = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(primaryDataSource());
                em.setPackagesToScan(
                        new String[] { "com.pzh.code.archyonix.model.db1",
                                "com.pzh.code.archyonix.service.db1",
                                "com.pzh.code.archyonix.service.impl.db1",
                                "com.pzh.code.archyonix.shop",
                                "com.pzh.code.archyonix.history"
                        });

                HibernateJpaVendorAdapter vendorAdapter
                        = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter);
                HashMap<String, Object> properties = new HashMap<>();
                properties.put("hibernate.hbm2ddl.auto",
                        env.getProperty("hibernate.hbm2ddl.auto"));
                properties.put("hibernate.dialect",
                        env.getProperty("hibernate.dialect"));
                em.setJpaPropertyMap(properties);

                return em;
        }

        @Primary
        @Bean
        public DataSource primaryDataSource() {

                DriverManagerDataSource dataSource
                        = new DriverManagerDataSource();
                dataSource.setDriverClassName(
                        env.getProperty("spring.datasource.driver-class-name"));
                dataSource.setUrl(env.getProperty("spring.datasource.url"));
                dataSource.setUsername(env.getProperty("spring.datasource.username"));
                dataSource.setPassword(env.getProperty("spring.datasource.password"));

                return dataSource;
        }

        @Primary
        @Bean
        public PlatformTransactionManager primaryTransactionManager() {

                JpaTransactionManager transactionManager
                        = new JpaTransactionManager();
                transactionManager.setEntityManagerFactory(
                        primaryEntityManager().getObject());
                return transactionManager;
        }

}
