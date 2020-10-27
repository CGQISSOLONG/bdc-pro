package com.admin.config.datasource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author luoxuan
 * @time 2019/1/24 16:16.
 * Description:第一数据源配置
 *  jpa 只配置一个数据源
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= {"com.bdc.entity", "com.admin.entity.primary"})
@MapperScan(basePackages ={"com.admin.dao.primary","com.bdc.dao"},
        sqlSessionFactoryRef = "primarySessionFactory")
public class PrimaryConfig {
    private static final String MAPPER_LOCATION = "classpath*:com/admin/dao/primary/*.xml";

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    /**
     * 获取jpa配置信息
     * @param dataSource
     * @return
     */
    private Map<String,String> getVendorProperties(DataSource dataSource){
        return  jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        //设置实体类所在位置
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.bdc.entity","com.admin.entity.primary")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource);
    }

    @Primary
    @Bean(name = "primarySessionFactory")
    public SqlSessionFactory primarySessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PrimaryConfig.MAPPER_LOCATION));
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setCallSettersOnNulls(true);
        conf.setMapUnderscoreToCamelCase(true);
        conf.setLogImpl(StdOutImpl.class);
        sessionFactory.setConfiguration(conf);
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}