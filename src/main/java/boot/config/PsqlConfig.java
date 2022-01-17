package boot.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author bty
 * @version 2022/1/17 12:20
 * @since JDK8
 */
@Configuration
@MapperScan(basePackages = "boot.mapper.psql", sqlSessionFactoryRef = "psqlSqlSessionFactory")
public class PsqlConfig {


        @Bean("psql")
        @ConfigurationProperties(prefix = "spring.datasource.psql") //读取application.yml中的配置参数映射成为一个对象
        public DataSource getDb1DataSource(){
            return DataSourceBuilder.create().build();
        }

        @Bean("psqlSqlSessionFactory")
        public SqlSessionFactory db1SqlSessionFactory(@Qualifier("psql") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

            // 设置数据源
            bean.setDataSource(dataSource);

            // 设置mapper.xml位置，必须设置。多数据源时在application.yml设置mapper-locations无效
            final Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/MysqlUserMapper.xml");
            bean.setMapperLocations(resources);

            // mybatis配置。多数据源时在application.yml设置mybatis.configuration无效
            final org.apache.ibatis.session.Configuration configuration = bean.getObject().getConfiguration();
            // final org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

            configuration.setMapUnderscoreToCamelCase(true);
            bean.setConfiguration(configuration);

            return bean.getObject();
        }

        @Primary
        @Bean("psqlSqlSessionTemplate")
        public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("psqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean("psqlSqlSessionTemplateForBatch")
        public SqlSessionTemplate mysqlSqlSessionTemplateForBatch(@Qualifier("psqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        }

}
