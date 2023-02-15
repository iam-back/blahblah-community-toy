package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"data.repository"})
public class PersistenceConfig {

    private final Resource[] resources = new ClassPathResource[]{
            new ClassPathResource("mybatis/mapper/user-mapper.xml"),
            new ClassPathResource("mybatis/mapper/board-mapper.xml")
    };

    @Bean
    public JndiObjectFactoryBean dataSource(){
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

        jndiObjectFactoryBean.setJndiName("jdbc/blahblah");
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        jndiObjectFactoryBean.setResourceRef(true);

        return jndiObjectFactoryBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource((DataSource) dataSource().getObject());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(resources);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public TransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource((DataSource) dataSource().getObject());

        return transactionManager;
    }
}
