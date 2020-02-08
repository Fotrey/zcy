package pers.zcy.myblogboot.config.persist;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@Configuration
public class MyJpaConfig {

    //解决 org.hibernate.LazyInitializationException--no session
    //貌似没用。。。。
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        openEntityManagerInViewFilter.setPersistenceUnitName("default");
        return openEntityManagerInViewFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(OpenEntityManagerInViewFilter openEntityManagerInViewFilter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(openEntityManagerInViewFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        registrationBean.setEnabled(true);
        return registrationBean;
    }

}
