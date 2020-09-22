package com.company;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Matrix implements Serializable {
    int[][] primitiveMatrix;

    public Matrix(int[][] oArray){
        primitiveMatrix = Arrays
                .stream(oArray)
                .map(row -> row.clone())
                .toArray(value -> new int[value][]);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : primitiveMatrix) {
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Collection<Index> getAdjacentIndices(final Index index){
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try{
            extracted = primitiveMatrix[index.row+1][index.column];
            list.add(new Index(index.row+1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column+1];
            list.add(new Index(index.row,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column];
            list.add(new Index(index.row-1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column-1];
            list.add(new Index(index.row,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}

        // Alachsonim
        try{
            extracted = primitiveMatrix[index.row-1][index.column-1];
            list.add(new Index(index.row-1,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column+1];
            list.add(new Index(index.row-1,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row+1][index.column-1];
            list.add(new Index(index.row+1,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row+1][index.column+1];
            list.add(new Index(index.row+1,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        return list;
    }

    public int getValue(Index index) {
        return primitiveMatrix[index.row][index.column];
    }

    public Matrix clone() {
        return new Matrix(this.primitiveMatrix.clone());
    }

    public Collection<Index> getReachables(Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        this.getAdjacentIndices(index).stream().filter(i-> getValue(i)==1)
                .map(neighbor->filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }

    public ArrayList<ArrayList<Index>> getMinimalPaths(ArrayList<ArrayList<Index>> paths){
        int sizeOfMinimalPath = Collections.min(paths, Comparator.comparing(ArrayList::size)).size();
        ArrayList<ArrayList<Index>> minimalPaths =
                paths
                        .stream()
                        .filter(p -> p.size() == sizeOfMinimalPath)
                        .collect(Collectors.toCollection(() -> new ArrayList<>()));

        return minimalPaths;
    }

    public ArrayList<ArrayList<Index>> getAllPaths(ArrayList<Index> p, Index source, Index destination){
        ArrayList<ArrayList<Index>> paths = new ArrayList<ArrayList<Index>>();
        getAllPathsRecursive(p, source, destination, paths);

        return paths;
    }

    public void getAllPathsRecursive(ArrayList<Index> p, Index source, Index destination, ArrayList<ArrayList<Index>> a){

        if(source.equals(destination)){
            p.add(destination);
            a.add(p);
            return;
        }

        p.add(source);
        Collection<Index> c = getReachables(source);
        for(Index i : c){
            if(!p.contains(i)) {
                getAllPathsRecursive(new ArrayList<>(p), i, destination, a);
            }
        }
    }

    public Collection<Index> getNeighbors(Index data) {
        return this.getAdjacentIndices(data);
    }

    public void setValue(Index source, int i) {
        this.primitiveMatrix[source.row][source.column] = i;
    }
}