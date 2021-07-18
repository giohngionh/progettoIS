package application;

import controller.ControllerViewGrattacielo;
import model.implProblema.Grattacielo;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class Applicazione {

    public Applicazione() {
        JFrame config = new JFrame("CONFIGURAZIONE GIOCO");
        JTextField infoConfig = new JTextField();
        infoConfig.setPreferredSize(new Dimension(30,30));
        JLabel display1 = new JLabel("Inserire il numero massimo di soluzioni che il sistema può trovare.");
        JLabel display2 = new JLabel( "Il numero effettivo di soluzioni ottenibili dipende dai vincoli generati.");
        JLabel display3 = new JLabel("");
        display3.setVisible(false);
        JPanel p = new JPanel();

        p.add(display1);
        p.add(display2);
        p.add(infoConfig);
        p.add(display3);
        config.add(p);
        config.setVisible(true);
        config.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        config.setSize(new Dimension(550, 150));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        config.setLocation(d.width / 2 - config.getSize().width / 2, d.height / 2 - config.getSize().height / 2);

        infoConfig.addActionListener(e -> {
            try {
                int val = Integer.parseInt(infoConfig.getText());
                if(val <1 || val > 30){
                    display3.setVisible(true);
                    display3.setText("Il valore inserito di soluzioni visualizzabili non è valido.");
                    infoConfig.setText("");
                } else {
                    esegui(val);
                    config.dispose();
                }
            } catch (NumberFormatException nfe){
                display3.setVisible(true);
                display3.setText("Non è stato  ancora inserito un numero.");
                infoConfig.setText("");
            }
        });
    }

    public static void main(String[] args) {
        Applicazione a = new Applicazione();
    }

    private void esegui(int val){
        Grattacielo g = new Grattacielo(5, val);
        ControllerViewGrattacielo cg = new ControllerViewGrattacielo(g);
        JButton nuovo = new JButton("NUOVO");
        JFrame finestra = new JFrame("GRATTACIELI");
        nuovo.addActionListener(e -> {
            Applicazione pv = new Applicazione();
            finestra.dispose();
        });
        finestra.add(nuovo, BorderLayout.NORTH);
        finestra.add(cg, BorderLayout.CENTER);
        finestra.getContentPane().add(cg);
        finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.pack();
        finestra.setVisible(true);
        finestra.setSize(new Dimension(550, 650));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        finestra.setLocation(d.width / 2 - finestra.getSize().width / 2, d.height / 2 - finestra.getSize().height / 2);
    }

}
