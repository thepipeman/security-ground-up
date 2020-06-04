package com.pipecrafts.securitygroundup.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(
  basePackages = {"com.pipecrafts.securitygroundup"},
  annotationClass = Mapper.class
)
public class MybatisConfig implements ResourceLoaderAware {

  @Setter
  private ResourceLoader resourceLoader;

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariDataSource dataSource(DataSourceProperties dataSourceProperties) {
    return (HikariDataSource) dataSourceProperties.initializeDataSourceBuilder()
      .build();
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
    Resource[] mapperLocations = getMybatisMapperFiles(resourceLoader);

    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setVfs(SpringBootVFS.class);
    factoryBean.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
    factoryBean.setMapperLocations(mapperLocations);
    factoryBean.setCache(null);
    return factoryBean;
  }

  @Bean
  public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
  }

  private static Resource[] getMybatisMapperFiles(ResourceLoader resourceLoader) {
    Resource[] mapperLocations;
    try {
      ResourcePatternResolver resourceResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
      mapperLocations = resourceResolver.getResources("classpath*:com.pipecrafts.securitygroundup/**/*Mapper.xml");
    } catch (IOException ex) {
      throw new RuntimeException("Failed to load mybatis mappers", ex);
    }
    return mapperLocations;
  }
}
