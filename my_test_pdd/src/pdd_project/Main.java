package pdd_project;

import pdd_project.view_controller.LoginFrame;


import java.io.IOException;

/**
 * Created by Alexey Sushko
 * Main класс гда запускаем поток в котором все будет выполняться
 */
public class Main {
    public static void main(String args[]) throws IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new LoginFrame();
            }
        });
    }


}
