package priv.zcy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TranscationalConfig {
    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
