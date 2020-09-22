package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Components {

    private Matrix matrix;

    public Components(Matrix matrix) {
        this.matrix = matrix;

    }

    public List<HashSet<Index>> GetAllComponents() {
        List<HashSet<Index>> components = new ArrayList<>();
        Matrix dupMatrix = this.matrix.clone();
        HashSet<Index> componentByIndex;
        for (int i = 0; i < dupMatrix.primitiveMatrix.length; i++) {
            for (int j = 0; j < dupMatrix.primitiveMatrix[1].length; j++) {
                componentByIndex = getComponentRecursive(dupMatrix, new Index(i, j), new HashSet<>());
                if (componentByIndex.size() > 0)
                    components.add(componentByIndex);
            }
        }
        return components;
    }

    public HashSet<Index> getComponentRecursive(Matrix matrix, Index source, HashSet<Index> p) {

        if (matrix.getValue(source) != 1) {
            return p;
        }

        p.add(source);
        matrix.setValue(source, 2);
        Collection<Index> c = matrix.getReachables(source);
        for (Index i : c) {
            return getComponentRecursive(matrix, i, new HashSet<>(p));
        }

        return p;
    }
}