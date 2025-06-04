package lii.buildmansterprojectmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BuildmansterTaskManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildmansterTaskManagerApiApplication.class, args);
    }

}
