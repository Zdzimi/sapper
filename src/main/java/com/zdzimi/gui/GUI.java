package com.zdzimi.gui;

import com.zdzimi.controler.Config;
import com.zdzimi.controler.Controller;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private Controller controller;
    private JFrame window;
    private JPanel boardPanel;

    public GUI(Controller controller) {
        this.controller = controller;
    }

    public void createWindow() {
        window = new JFrame("saper");
        window.setMinimumSize(new Dimension(100, 100));
        window.setResizable(true);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(100, 20));
        menuBar.setBorder(null);
        menuBar.setBackground(Color.RED);
        window.setJMenuBar(menuBar);

        JMenu newGame = new JMenu("new game");
        menuBar.add(newGame);

        JMenuItem easy = new JMenuItem("easy");
        easy.setBorder(null);
        easy.setBackground(Color.GREEN);

        JMenuItem medium = new JMenuItem("medium");
        medium.setBorder(null);
        medium.setBackground(Color.GREEN);

        JMenuItem hard = new JMenuItem("hard");
        hard.setBorder(null);
        hard.setBackground(Color.GREEN);

        newGame.add(easy);
        easy.setActionCommand("new-" + Config.E.name());
        easy.addActionListener(controller);

        newGame.add(medium);
        medium.setActionCommand("new-" + Config.M.name());
        medium.addActionListener(controller);

        newGame.add(hard);
        hard.setActionCommand("new-" + Config.H.name());
        hard.addActionListener(controller);

        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void refreshWindow(Config config, BoardPanel boardPanel) {
        window.setMinimumSize(new Dimension(config.getWidth()*30,config.getHeight()*30));
        if (this.boardPanel != null){
            window.remove(this.boardPanel);
        }
        this.boardPanel = boardPanel;
        window.add(this.boardPanel);
    }

    public void youWinInfo() {
        JOptionPane.showMessageDialog(null,"Winner");
    }
}
