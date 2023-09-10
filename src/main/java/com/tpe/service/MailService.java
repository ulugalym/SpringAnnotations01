package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component//class level: bu class'in objesinin yontemi Spring tarafindan olacak
@Scope("singleton")
public class MailService implements MessageService{

    @PostConstruct//classin const cagrildiktan sonra method'u cagirir
    public void postConstruct(){
        System.out.println("MailService objesi olusturuldu");
    }

    @PreDestroy//obje imha edilmeden once methodu cagirir
    public void preDestroy(){
        System.out.println("MailService objesi imha edildi.");
    }


    //========================================================================================================
    //field injection
    @Autowired//repo objesinin MailService objesine enjekte edilmesini saglar:Dependence Injection
    @Qualifier("fileRepository")//1 den fazla implementasyonu belirleyici gorevi gorur
    private Repository repo;

    //setter injection
    @Autowired
    @Qualifier("fileRepository")
    private Repository repo1;

    public void setRepo1(Repository repo1) {
        this.repo1 = repo1;
    }


    //Constructor Injection: daha guvenli, daha anlasilir, test etmek daha kolay.

    private Repository repo2;
    @Autowired//classin sadece 1 tane constructor'u varsa optional olur. 1den fazla ise @Autowired zorumlu olur
    public MailService(@Qualifier("fileRepository") Repository repo2) {
        this.repo2 = repo2;
    }

    public MailService() {
    }
//==================================================================================================================
    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir mail servisiyim. Mesajiniz: "+message.getMessage());
    }

    @Override
    public void saveMessage(Message message) {

        repo.save(message);
        repo1.save(message);
        repo2.save(message);
    }
}
