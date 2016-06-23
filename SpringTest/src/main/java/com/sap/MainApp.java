package com.sap;

/**
 * Created by i855631 on 6/7/16.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.*;

public class MainApp {
    public static void main(String[] args){
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj = (HelloWorld)context.getBean("helloWorld");
        obj.getMessage();
    }
}