package com.company;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MatrixIHandler implements IHandler {

    private Matrix matrix;
    private Index start, end;

    public MatrixIHandler() {
        this.resetParams();
    }

    private void resetParams() {
        this.matrix = null;
        this.start = null;
        this.end = null;
    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {
        System.out.println("server::start handle");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        this.resetParams();

        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop": {
                    dowork = false;
                    break;
                }
                // Get All Components
                case "Task1": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    this.matrix = new Matrix(primitiveMatrix);
                    Components c = new Components(this.matrix);
                    List<HashSet<Index>> allComponents = c.GetAllComponents();
                    objectOutputStream.writeObject(allComponents);
                    break;
                }
                // Get All Paths
                case "Task2": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    Index source = (Index) objectInputStream.readObject();
                    Index destination = (Index) objectInputStream.readObject();
                    this.matrix = new Matrix(primitiveMatrix);
                    Paths p = new Paths(this.matrix, source, destination);
                    ArrayList<ArrayList<Index>> allPaths = p.GetAllPaths();
                    objectOutputStream.writeObject(allPaths);
                    break;
                }
                // Get minimum paths
                case "Task3": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();

                    Index source = (Index) objectInputStream.readObject();
                    Index destination = (Index) objectInputStream.readObject();
                    this.matrix = new Matrix(primitiveMatrix);
                    Paths p = new Paths(this.matrix, source, destination);
                    ArrayList<ArrayList<Index>> minimalPaths = p.GetMinimumPath();
                    objectOutputStream.writeObject(minimalPaths);
                    break;
                }
                // Battleships Validation
                case "Task4": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    this.matrix = new Matrix(primitiveMatrix);
                    BattleShips battleShips = new BattleShips(this.matrix);
                    objectOutputStream.writeObject(battleShips.getBattleShips());
                    break;
                }
            }
        }
    }
}