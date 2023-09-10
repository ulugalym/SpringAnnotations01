package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.Random;

@Component("sms_service")
public class SMSService implements MessageService{

    @PostConstruct
    public void postConstruct(){
        System.out.println("SMSService objesi olusturuldu.");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("SMSService objesi imha edildi.");
    }


    @Autowired
    private Random random;

    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir SMS serviciyim. Mesajiniz: "+message.getMessage());
    }

    @Override
    public void saveMessage(Message message) {

        System.out.println(random.nextInt(100));
    }

    @Value("${email}")
    private String email;
    @Value("${phone}")
    private String phone;
    public void printContact(){
        System.out.println("email : "+email);
        System.out.println("phone : "+phone);
    }


    @Autowired
    private Properties properties;
    public void printProperties(){
        System.out.println("email bilgisi: "+properties.get("mymail"));
        System.out.println("phone bilgisi: "+properties.get("phone"));
    }
}
