package com.ecommerce.paymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Slf4j
public class PaymentServiceSpringBootApp
{
    public static void main( String[] args )
    {
        log.info( "Payment Service Started" );
        SpringApplication.run(PaymentServiceSpringBootApp.class);
    }
}
