package com.bai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class,args);
    }
}
