package demo;

import java.util.UUID;

/**
 * Created by i855631 on 6/29/16.
 */
class Message {
    String id = UUID.randomUUID().toString();
    String content;
    public Message(){
        content = null;
    }
    public Message(String content){
        this.content = content;
    }
    public String getId(){
        return id;
    }
    public String getContent(){
        return content;
    }
}