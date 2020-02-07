package com.zdzimi.controler;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater( () ->{
            Controller controller = new Controller();
            controller.run();
        });
    }
}
