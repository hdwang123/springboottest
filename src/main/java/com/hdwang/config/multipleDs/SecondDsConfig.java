package com.hdwang.config.multipleDs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by hdwang on 2017-06-16.
 * 第二个数据源配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hdwang.dao.datajpa.secondDs", entityManagerFactoryRef = "secondEntityManagerFactory",transactionManagerRef = "secondTransactionManager")
public class SecondDsConfig {

    @Bean
    @ConfigurationProperties("second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("second.datasource")
    public DataSource secondDataSource() {
        return secondDataSourceProperties().initializeDataSourceBuilder().build();
    }

    /**
     * 实体管理对象
     * @param builder  由spring注入这个对象，首先根据type注入（多个就取声明@Primary的对象），否则根据name注入
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .packages("com.hdwang.entity.dbSecond")
                .persistenceUnit("secondDs")
                .build();
    }

    /**
     * 事物管理对象
     * @param secondEntityManagerFactory 实体管理工厂对象（按照名称注入）
     * @return 平台事物管理器
     */
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("secondEntityManagerFactory")LocalContainerEntityManagerFactoryBean secondEntityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondEntityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean(name="jdbcTemplate2")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(secondDataSource());
    }

    @Bean(name = "transactionTemplate2")
    public TransactionTemplate transactionTemplate(@Qualifier("secondTransactionManager")PlatformTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }


    /**
     @Autowired
     private Environment env;

     @Bean
     public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
     LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
     em.setDataSource(secondDataSource());
     em.setPackagesToScan(new String[] {"com.hdwang.entity.dbSecond"});

     HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
     em.setJpaVendorAdapter(vendorAdapter);
     HashMap<String, Object> properties = new HashMap<String, Object>();
     properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
     properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
     //        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
     em.setJpaPropertyMap(properties);
     em.setPersistenceUnitName("secondDs");
     return em;
     }

     @Bean(name = "secondTransactionManager")
     public PlatformTransactionManager transactionManager(){
     JpaTransactionManager transactionManager = new JpaTransactionManager();
     transactionManager.setEntityManagerFactory(secondEntityManagerFactory().getObject());
     return transactionManager;
     }

     @Bean(name="transactionTemplate2")
     public TransactionTemplate transactionTemplate(){
     return new TransactionTemplate(transactionManager());
     }

     @Bean(name = "entityManager2")
     public EntityManager entityManager() {
     return secondEntityManagerFactory().getObject().createEntityManager();
     }
     **/

}
