package pers.zcy.mybloginteract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"pers.zcy.myblogauthorize.dao","pers.zcy.myblogboot.persist"})
@EntityScan(basePackages = "pers.zcy.myblogboot.entity")
@ComponentScan(
        basePackages =
                {"pers.zcy"}
                )
public class BlogInteractApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogInteractApplication.class, args);
    }

}
