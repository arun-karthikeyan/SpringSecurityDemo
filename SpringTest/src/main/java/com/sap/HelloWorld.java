package com.sap;

/**
 * Created by i855631 on 6/7/16.
 */
public class HelloWorld {
    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public void getMessage(){
        System.out.println("Your message is : "+this.message);
    }
}
