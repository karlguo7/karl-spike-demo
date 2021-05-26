package cn.karlguo.spike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;


/*
 * @Classname SpikeDemoApp
 * @Date 2021/5/11
 * @author karlguo
 * @Description
 */

@SpringBootApplication/*(scanBasePackages = {"cn.karlguo"})*/
//@MapperScan("cn.karlguo.spike.mapper")
public class SpikeDemoApp {
    public static void main(String[] args) {

        ApplicationContext run = SpringApplication.run(SpikeDemoApp.class, args);

        /*System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = run.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }
}

