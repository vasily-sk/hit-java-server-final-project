package com.company;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket =new Socket("127.0.0.1",8010);
        System.out.println("client::Socket");

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream toServer=new ObjectOutputStream(outputStream);
        ObjectInputStream fromServer=new ObjectInputStream(inputStream);

        // Task 1
        int [][] Task1Matrix = {
                {1, 1, 0, 1},
                {1, 0, 0, 0},
                {0, 0, 1, 1},
                {1, 0, 1, 1},
                {1, 0, 0, 0}
        };
        toServer.writeObject("Task1");
        toServer.writeObject(Task1Matrix);
        List<HashSet<Index>> allComponents = (List<HashSet<Index>>) fromServer.readObject();

        System.out.println("client:: Task 1 All components:: "+ allComponents);

        // Task 2
        int [][] Task2Matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 0, 1},
        };
        Index source = new Index(0,0);
        Index dest = new Index(2,2);

        toServer.writeObject("Task2");
        toServer.writeObject(Task2Matrix);
        toServer.writeObject(source);
        toServer.writeObject(dest);
        ArrayList<ArrayList<Index>> allPaths = (ArrayList<ArrayList<Index>>) fromServer.readObject();

        System.out.println("client:: Task 2 All Paths:: "+ allPaths);

        // Task 3
        int [][] Task3Matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 0, 1},
        };

        source = new Index(0,0);
        dest = new Index(2,2);

        toServer.writeObject("Task3");
        toServer.writeObject(Task3Matrix);
        toServer.writeObject(source);
        toServer.writeObject(dest);
        ArrayList<ArrayList<Index>> minimalPaths = (ArrayList<ArrayList<Index>>) fromServer.readObject();

        System.out.println("client:: Task 3 Minimal Paths:: "+ minimalPaths);

        // Task 4
        int [][] Task4Matrix = {
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}
        };

        toServer.writeObject("Task4");
        toServer.writeObject(Task4Matrix);
        int numberOfBattleShips = (int)fromServer.readObject();
        if(numberOfBattleShips > 0) {
            System.out.println("client:: Task 4 BattleShips:: " + numberOfBattleShips );
        }
//        else {
//            System.out.println("client:: Task 4 BattleShips:: battle ships board is invalid");
//        }

        toServer.writeObject("stop");

        System.out.println("client::Close all streams!!!!");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("client::Close socket!!!!");
    }
}