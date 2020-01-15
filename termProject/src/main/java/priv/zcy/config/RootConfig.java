package priv.zcy.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import priv.zcy.pojo.data_wrapper_utils.MemberInfoFormat;
import priv.zcy.pojo.data_wrapper_utils.TokenFormat;
import priv.zcy.pojo.data_wrapper_utils.WrapperDataFormat;

@Configuration
@Import({DataSourceConfig.class,TranscationalConfig.class})
public class RootConfig {
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    WrapperDataFormat wrapperDataFormat(){
        WrapperDataFormat wrapperDataFormat = new WrapperDataFormat();
        return wrapperDataFormat;
    }

    @Bean
    TokenFormat tokenFormat(){
        TokenFormat tokenFormat = new TokenFormat();
        return tokenFormat;
    }

    @Bean
    MemberInfoFormat memberInfoFormat(){
        MemberInfoFormat memberInfoFormat = new MemberInfoFormat();
        return memberInfoFormat;
    }
}
