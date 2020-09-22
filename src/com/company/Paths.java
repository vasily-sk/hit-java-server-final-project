package com.company;

import java.util.ArrayList;

public class Paths {
    private Matrix matrix;
    private Index source;
    private Index dest;

    public Paths(Matrix matrix, Index source, Index dest) {
        this.source = source;
        this.dest = dest;
        this.matrix = matrix;
    }

    public ArrayList<ArrayList<Index>> GetAllPaths() {
        return this.matrix.getAllPaths(new ArrayList<Index>(), this.source, this.dest);
    }

    public ArrayList<ArrayList<Index>> GetMinimumPath() {
        return this.matrix.getMinimalPaths(GetAllPaths());
    }
}
