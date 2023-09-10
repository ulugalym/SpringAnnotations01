package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Random;

@Configuration//bu classda config olacak
@ComponentScan("com.tpe") //bu packagedaki component'leri tara, default: "com.tpe"(AppConfig in icinde oldugu package)
@PropertySource("classpath:application.properties")//properties dosyasinin kaynak olarak okunmasini saglar
public class AppConfiguration {


    //Spring interface'i Environment:application.properties icindeki degiskenlere ve
    //uygulamanin calistigi tum ortam degiskenlerine ulasmamizi saglar.
    @Autowired
    private Environment environment;


    @Bean//thirdParty classlardan bean olusturulur
    public Random random(){
        return new Random();
    }


    //value annotation ile yaptigimiz islem icin Properties classini kullanalim
    @Bean
    public Properties properties(){
        Properties properties=new Properties();
        properties.put("mymail",environment.getProperty("email"));
        properties.put("phone",environment.getProperty("phone"));
        return properties;
    }


}
