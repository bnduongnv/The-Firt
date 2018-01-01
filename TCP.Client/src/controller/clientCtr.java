/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Student;

/**
 *
 * @author Blank
 */
public class clientCtr {
    private int port;
    private String host;
    private Socket mySocket;
    public clientCtr(){
        port=8888;
        host="localhost";
        
    }
    public void OpenSocket(){
        try{
            mySocket =new Socket(host,port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ClosteConnection(){
        try{
            mySocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendStudent(Student s){
        try{
            ObjectOutputStream oos=new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(s);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getResult(){
        String res="";
        try{
            ObjectInputStream ois=new ObjectInputStream(mySocket.getInputStream());
            res=(String)ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
