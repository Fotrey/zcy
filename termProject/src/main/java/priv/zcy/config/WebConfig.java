package priv.zcy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@PropertySource("classpath:viewResolver.properties")
@ComponentScan(value = {"priv.zcy.controller","priv.zcy.biz","priv.zcy.dao"})
@EnableAspectJAutoProxy
@MapperScan("priv.zcy.dao")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${viewPrefix}")
    private String prefix;

    @Value("${viewSuffix}")
    private String suffix;

    @Bean
    InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//    }
}
