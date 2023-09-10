package com.tpe.repo;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class DbRepository implements Repository{
    @Override
    public void save(Message message) {
        System.out.println("Mesajiniz DB ye kaydedilyor.");
    }
}
