package java4.auction_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class BeanConfig implements WebMvcConfigurer{

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path brandUploadDir = Paths.get("./images");
        String brandUpLoadPath = brandUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + brandUpLoadPath + "/");
    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        // host & port of GOOGLE
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        // get username & password (password of application in GoogleManagement) of admin or me
//        mailSender.setUsername("fptintern@gmail.com");
//        mailSender.setPassword("fptintern123");
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "false");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }
}
