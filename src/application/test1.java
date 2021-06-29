package application;


import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;


import controller.ControllerGrattacielo;
import model.backtrack.Posizione;
import model.implProblema.Cella;
import model.implProblema.Grattacielo;
import view.GraphicObjectView;

import javax.swing.*;

public class test1 {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
        run();

    }

    public static void run(){
        ControllerGrattacielo cg = new ControllerGrattacielo(new Grattacielo(5,10));
        Cella[] celle = cg.getCelle();

        for(Cella c : celle) {
            c.addGraphicObjectListener(new GraphicObjectView());
            //c.setText("a");
        }

        JFrame f = new JFrame("Prova ControllerGrattacielo");
        f.add(cg);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(600,600));
        f.pack();
        f.setResizable(false);
        f.setVisible(true);

    }
    
}
