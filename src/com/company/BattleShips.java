package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class BattleShips {

    private Matrix matrix;

    public BattleShips(Matrix matrix) {
        this.matrix = matrix;
    }

    public int getBattleShips() {
        Components c = new Components(this.matrix);
        List<HashSet<Index>> battleShips = c.GetAllComponents();

        return battleShips.size();
    }

    public boolean isBattleShipsValid(List<HashSet<Index>> battleShips) {
        boolean isValid = true;

        for (int i = 0; i < battleShips.size() && isValid; i++) {
            HashSet<Index> bs = battleShips.get(i);
            int maxRow = Collections.max(bs, Comparator.comparing(Index::getRow)).getRow();
            int maxCol = Collections.max(bs, Comparator.comparing(Index::getColumn)).getColumn();
            int minRow = Collections.min(bs, Comparator.comparing(Index::getRow)).getRow();
            int minCol = Collections.min(bs, Comparator.comparing(Index::getColumn)).getColumn();

            if (((maxCol - minCol) + 1) * ((maxRow - minRow) + 1) != bs.size()) {
                isValid = false;
            }
        }

        return isValid;
    }
}
