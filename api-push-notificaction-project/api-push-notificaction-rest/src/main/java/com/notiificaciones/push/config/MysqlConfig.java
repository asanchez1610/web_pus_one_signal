package com.notiificaciones.push.config;

import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:parametros.properties")
@MapperScan(basePackages={"com.notiificaciones.push.dao"})
public class MysqlConfig {
	

  @Value("#{ environment['jndi.url']?:'' }")
  private String jndiUrl;

  @Bean(name = "mysqlSourceMysql")
  @Primary
  public DataSource mysqlSourceMysql() throws NamingException {
	  DataSource dataSource = null;
      JndiTemplate jndi = new JndiTemplate();
      try {
          dataSource = jndi.lookup(jndiUrl, DataSource.class);
      } catch (NamingException e) {
          e.printStackTrace();
      }
      return dataSource;
  }

  @Bean(name="transaccionMysql")
  @Primary
  public DataSourceTransactionManager transaccionOracle() throws NamingException {
    return new DataSourceTransactionManager(mysqlSourceMysql());
  }

	@Bean(name = "sqlSessionFactoryMysql")
	@Primary
	public SqlSessionFactory sqlSessionFactoryOracle() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mysqlSourceMysql());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/META-INF/dao-config.xml"));
		return sqlSessionFactoryBean.getObject();
		
	}
  
}
