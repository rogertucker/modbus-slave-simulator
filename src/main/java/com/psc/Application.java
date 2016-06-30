package com.psc;


import com.psc.modbus.ModbusSlaveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.psc.modbus", "com.psc.sensor.controller"})
@EnableAutoConfiguration
@PropertySource("classpath:application-default.properties")
public class Application{
    @Autowired


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext ctx = app.run(args);
        ModbusSlaveHandler modbusSlaveHandler = (ModbusSlaveHandler)ctx.getBean("modbusSlaveHandler");
        modbusSlaveHandler.addProcessImage("raspberry.pi.slave");
        modbusSlaveHandler.run();


    }

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory();
    }

}
