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
        basePackages = "com.pzh.code.archyonix.repository.db2",
        entityManagerFactoryRef = "mgEntityManager",
        transactionManagerRef = "mgTransactionManager"
)
public class SpringMGDataBaseConfig {
        @Autowired
        private Environment env;

        @Bean
        public LocalContainerEntityManagerFactoryBean mgEntityManager() {
                LocalContainerEntityManagerFactoryBean em
                        = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(mgDataSource());
                em.setPackagesToScan(
                        new String[] { "com.pzh.code.archyonix.model.db2",
                                "com.pzh.code.archyonix.service.db2",
                                "com.pzh.code.archyonix.service.impl.db2"
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


        @Bean
        public DataSource mgDataSource() {

                DriverManagerDataSource dataSource
                        = new DriverManagerDataSource();
                dataSource.setDriverClassName(
                        env.getProperty("spring.mg-datasource.driver-class-name"));
                dataSource.setUrl(env.getProperty("spring.mg-datasource.url"));
                dataSource.setUsername(env.getProperty("spring.mg-datasource.username"));
                dataSource.setPassword(env.getProperty("spring.mg-datasource.password"));

                return dataSource;
        }

        @Bean
        public PlatformTransactionManager mgTransactionManager() {

                JpaTransactionManager transactionManager
                        = new JpaTransactionManager();
                transactionManager.setEntityManagerFactory(
                        mgEntityManager().getObject());
                return transactionManager;
        }
}
