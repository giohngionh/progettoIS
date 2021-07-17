package application;


import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import model.implProblema.Grattacielo;
import controller.ControllerViewGrattacielo;

import javax.swing.*;

public class test1 {
    public static void main(String[] args){
        JFrame f = new JFrame("Prova ControllerGrattacielo");
        JButton chiudi = new JButton("CHIUDI");
        chiudi.addActionListener(e -> {
            f.dispose();
        });
        JPanel p = new JPanel();
        p.add(chiudi);
        f.add(p);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(600,600));
        f.pack();
        f.setResizable(false);
        f.setVisible(true);

    }
    
}
