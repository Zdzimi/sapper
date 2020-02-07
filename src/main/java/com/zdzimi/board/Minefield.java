package com.zdzimi.board;

import com.zdzimi.controler.Config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Minefield {

    private int[][] fields;
    private int mines;

    public Minefield(Config config) {
        this.fields = new int[config.getWidth()][config.getHeight()];
        this.mines = config.getBomb();
    }

    public void generateMineField() {
        Set<Field> fieldSet = new HashSet<>();
        Random random = new Random();
        while (fieldSet.size() < this.mines) {
            int row = random.nextInt(this.fields.length);
            int col = random.nextInt(this.fields[0].length);
            fieldSet.add(new Field(row,col));
        }

        Iterator<Field> iterator = fieldSet.iterator();
        while (iterator.hasNext()){
            Field f = iterator.next();
            fields[f.getRow()][f.getCol()] = 88;
            incrementFieldsArroundMe(f);
        }
    }

    private void incrementFieldsArroundMe(Field f) {
        int minRow = getMinimumRow(f);
        int maxRow = getMaximumRow(f);

        int minCol = getMinimumCol(f);
        int maxCol = getMaximumCol(f);

        for (int i = minRow; i < maxRow + 1; i++) {
            for (int j = minCol; j < maxCol + 1; j++) {
                if (fields[i][j] != 88) {
                    fields[i][j]++;
                }
            }
        }
    }

    public int getMaximumCol(Field f) {
        return f.getCol() == fields[0].length -1 ? f.getCol() : f.getCol() + 1;
    }

    public int getMinimumCol(Field f) {
        return f.getCol() == 0 ? 0 : f.getCol() - 1;
    }

    public int getMaximumRow(Field f) {
        return f.getRow() == fields.length -1 ? f.getRow() : f.getRow() + 1;
    }

    public int getMinimumRow(Field f) {
        return f.getRow() == 0 ? 0 : f.getRow() - 1;
    }

    public String getSymbolFromField(Field field) {
        if (fields[field.getRow()][field.getCol()] == 88) {
            return "*";
        }
        if (fields[field.getRow()][field.getCol()] == 0) {
            return "";
        }
        return String.valueOf(fields[field.getRow()][field.getCol()]);
    }
}
