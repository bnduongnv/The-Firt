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
import java.util.ArrayList;
import model.Student;
import view.serverView;

/**
 *
 * @author Blank
 */
public class serverCtr {

    private int port;
    private String host;
    private serverDAO dao;
    private ServerSocket myServer;
    private ArrayList<Socket> list;

    public serverCtr() {
        port = 8888;
        host = "localhost";
        dao = new serverDAO();
        list = new ArrayList<>();
        opentSocket();
        while (true) {
            try {
                Socket s = myServer.accept();
                list.add(s);
                execute(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void opentSocket() {
        try {
            myServer = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student receiveStudent(Socket s) {
        Student ss = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ss = (Student) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }

    public void sendResult(String res) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(list.get(list.size() - 1).getOutputStream());
            oos.writeObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(Socket s) {
        try {
            Student ss = receiveStudent(s);
            if (dao.addStudent(ss)) {
                sendResult("ok");
                new serverView().showMessage("success!");
            } else {
                sendResult("failed");
                new serverView().showMessage("Failed!-Lỗi lúc thêm vào csdl");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResult("ok");
            new serverView().showMessage("Success!");
        }
    }

}
