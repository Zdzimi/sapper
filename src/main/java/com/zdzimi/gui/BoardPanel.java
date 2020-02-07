package com.zdzimi.gui;

import com.zdzimi.board.Field;
import com.zdzimi.controler.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {

    private JButton[][] minefield;
    private ActionListener listener;

    private Font font = new Font("Serif",Font.BOLD,12);

    public BoardPanel(ActionListener listener) {
        this.listener = listener;
    }


    public void initialize(Config config) {
        this.setLayout(new GridLayout(config.getWidth(), config.getHeight(), 1, 1));
        setMinefield(config);
        setActionCommandForFields();
        setActionListenerForFields();
        setFontForButtons();
    }

    private void setFontForButtons() {
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield[row].length; col++) {
                minefield[row][col].setFont(font);
            }
        }
    }

    private void setActionListenerForFields() {
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield[row].length; col++) {
                minefield[row][col].addActionListener(listener);
            }
        }
    }

    private void setActionCommandForFields() {
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield[row].length; col++) {
                minefield[row][col].setActionCommand("coords-" + row + "-" + col);
            }
        }
    }

    private void setMinefield(Config config) {
        minefield = new JButton[config.getWidth()][config.getHeight()];
        JButton button;
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield[row].length; col++) {
                button = new JButton();
                minefield[row][col] = button;
                this.add(button);
            }
        }
    }

    public void revalField(String fieldSymbol, Field field) {
        minefield[field.getRow()][field.getCol()].setText(fieldSymbol);
        minefield[field.getRow()][field.getCol()].setBackground(fieldSymbol.equals("*") ? Color.RED : Color.GREEN);
        minefield[field.getRow()][field.getCol()].setEnabled(false);
    }

    public void disableAllFields() {
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield[row].length; col++) {
                minefield[row][col].setEnabled(false);
            }
        }
    }
}
