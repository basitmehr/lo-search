package com.lo.search.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySources({
    @PropertySource("classpath:mysql-queries.properties")
})
public class DatabaseConfig {
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.user}")
	private String jdbcUser;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
    @Bean
    public BasicDataSource datasource() {
    	BasicDataSource basicDataSource = new BasicDataSource();
    	basicDataSource.setUrl(jdbcUrl);
    	basicDataSource.setUsername(jdbcUser);
    	basicDataSource.setPassword(jdbcPassword);
    	basicDataSource.setMaxIdle(10);
    	basicDataSource.setMaxTotal(15);
    	basicDataSource.setMaxWaitMillis(5000);
    	basicDataSource.setTestOnBorrow(true);
    	basicDataSource.setMinIdle(5);
    	
        return basicDataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(datasource());
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(datasource());
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource());
        return transactionManager;
    }

}
