package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SMSService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class MyApplication {
    public static void main(String[] args) {
        Message message=new Message();
        message.setMessage("Spring ile uygulama gelistirme HARIKA olacak: ");

        //config classini okur, component scan ile package'deki tum component'leri tarar ve
        //herbirisini sadece 1 tane bean olusturur, context'te hazir bekler, bean istendiginde
        //icine bagimlilik enjekte edilerek gonderilir.
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);


        MessageService service=context.getBean(MailService.class);//Spring akilli
        service.sendMessage(message);

        MessageService service1=context.getBean(SMSService.class);
        service1.sendMessage(message);

        MessageService service2=context.getBean("mailService",MessageService.class);
        service2.sendMessage(message);

        MessageService service3=context.getBean("sms_service",MessageService.class);
        service3.sendMessage(message);
        service3.saveMessage(message);

        //interface i implemente eden birden fazla class varsa ismi belirtilmeli, aksi halde NoUniqueBean...
        System.out.println("===========================================");

        MessageService service4=context.getBean(MailService.class);
        service4.sendMessage(message);
        service4.saveMessage(message);

        //Random random=new Random();

        Random random=context.getBean(Random.class);
        System.out.println(random.nextInt(100));

        System.out.println("==================================================");


        //bean scope
        //singleton
        //sadece 1 tane obje uretilir ve heryerde kullanilir
        //beanin yonetiminden tamamen Spring sorumludur.


        //prototype
        //her istekde yeni obje olusturulur.
        //beanin life cycle'indan tamamen Spring sorumlu degildir.
        //beanin imhasindan Spring sorumlu DEGILDIR>


        MessageService service5=context.getBean(MailService.class);
        MessageService service6=context.getBean(MailService.class);

        if(service5==service6){
            System.out.println("Ayni referansli objeler");
            System.out.println(service5);
            System.out.println(service4);
            System.out.println(service3);
            System.out.println(service2);
            System.out.println(service1);
            System.out.println(service);
            System.out.println(service6);
        }else {
            System.out.println("Farkli referansli objeler");
            System.out.println(service5);
            System.out.println(service4);
            System.out.println(service3);
            System.out.println(service2);
            System.out.println(service1);
            System.out.println(service);
            System.out.println(service6);
        }

        SMSService service7=context.getBean(SMSService.class);
        service7.printContact();
        service7.printProperties();

        context.close();

    }
}
