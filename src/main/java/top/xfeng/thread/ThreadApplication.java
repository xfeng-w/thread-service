package top.xfeng.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuefeng.wang
 * @date 2020-06-30
 */
@EnableSwagger2
@SpringBootApplication
public class ThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }
}
