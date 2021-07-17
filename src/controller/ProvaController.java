package controller;

import javax.swing.*;
import java.awt.*;
import model.implProblema.Grattacielo;

public class ProvaController {
    public ProvaController(){
        Grattacielo g = new Grattacielo(5,10);
        ControllerViewGrattacielo cg = new ControllerViewGrattacielo(g);
        JButton nuovo = new JButton("NUOVO");
        JFrame finestra = new JFrame("GRATTACIELI");
        nuovo.addActionListener(e -> {
            ProvaController pv = new ProvaController();
            finestra.dispose();
        });
        finestra.add(nuovo, BorderLayout.NORTH);
        finestra.add(cg, BorderLayout.CENTER);
        finestra.getContentPane().add(cg);
        finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.pack();
        finestra.setVisible(true);
        finestra.setSize(new Dimension(550,650));
        Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
        finestra.setLocation(d.width/2-finestra.getSize().width/2, d.height/2-finestra.getSize().height/2);
    }
    public static void main(String[] args) {
        ProvaController pv = new ProvaController();
    }

}
