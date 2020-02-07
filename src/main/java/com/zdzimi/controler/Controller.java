package com.zdzimi.controler;

import com.zdzimi.board.Field;
import com.zdzimi.board.Minefield;
import com.zdzimi.gui.BoardPanel;
import com.zdzimi.gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private GUI gui;
    private Minefield minefield;
    private BoardPanel boardPanel;
    private int counter;

    {
        gui = new GUI(this);
    }

    public void run() {
        gui.createWindow();
    }

    public void actionPerformed(ActionEvent e) {
        String[] tab = e.getActionCommand().split("-");
        switch (tab[0]){
            case "new" :
                startNewGame(Config.valueOf(tab[1]));
                break;
            case "coords" :
                checkField(tab[1],tab[2]);
                break;
        }

    }

    private void checkField(String s, String s1) {
        Field field = makeFieldFromCoords(s,s1);
        String fieldSymbol = minefield.getSymbolFromField(field);
        boardPanel.revalField(fieldSymbol,field);
        if (--counter == 0){
            boardPanel.disableAllFields();
            gui.youWinInfo();
        }
//        if (fieldSymbol.equals("")){
//            checkFieldsArround(field);
//        }
        if (fieldSymbol.equals("*")){
            boardPanel.disableAllFields();
        }
    }

//    private void checkFieldsArround(Field field) {
//        int minRow = minefield.getMinimumRow(field);
//        int maxRow = minefield.getMaximumRow(field);
//
//        int minCol = minefield.getMinimumCol(field);
//        int maxCol = minefield.getMaximumCol(field);
//
//        for (int i = minRow; i < maxRow + 1; i++) {
//            for (int j = minCol; j < maxCol + 1; j++) {
//                checkField(String.valueOf(i),String.valueOf(j));
//            }
//        }
//    }

    private Field makeFieldFromCoords(String s, String s1) {
        return new Field(Integer.parseInt(s), Integer.parseInt(s1));
    }

    private void startNewGame(Config config) {
        minefield = new Minefield(config);
        minefield.generateMineField();
        boardPanel = new BoardPanel(this);
        boardPanel.initialize(config);
        gui.refreshWindow(config, boardPanel);
        counter = config.getWidth() * config.getHeight() - config.getBomb();
    }
}
