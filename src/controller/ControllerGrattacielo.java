package controller;

import model.backtrack.Posizione;
import model.implProblema.Cella;
import model.implProblema.Grattacielo;

import java.awt.*;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class ControllerGrattacielo extends JPanel{

    private JTextField console;
    private JPanel scacchiera;
    private JPanel comandi;
    private Cella[] celle;

    private Grattacielo g;


    public ControllerGrattacielo(Grattacielo g){
        this.g = g;
        celle = g.getM();


        console = new JTextField();
        scacchiera = new JPanel(new GridLayout(7,7));
        comandi = new JPanel((new GridLayout(1,3)));
        console.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    //  comandi.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        console.setPreferredSize(new Dimension(400,50));
        scacchiera.setPreferredSize(new Dimension(400,400));
        comandi.setPreferredSize(new Dimension(400,50));


        console.setText("Console");
        console.setEditable(false);

        JButton controlla = new JButton("CONTROLLA");
        controlla.addActionListener(e -> {
            if(!g.controllaVincoli()){
                setDisplayMessage("Soluzione non corretta.");
            } else {
                setDisplayMessage("Soluzione corretta!");
            }
        });
        controlla.setMaximumSize(new Dimension(100,30));
        comandi.add(controlla);

        JButton risolvi = new JButton("RISOLVI");
        risolvi.addActionListener(e -> {
            g.risolvi();
        });
        risolvi.setMaximumSize(new Dimension(100,30));
        comandi.add(risolvi);

        JButton nuovo = new JButton("NEW GAME");
        nuovo.addActionListener(e -> {
            restart();
        });
        nuovo.setMaximumSize(new Dimension(100,30));
        comandi.add(nuovo);


        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        int countN=0, countS=0, countE=0, countO=0, tmp;
        JTextField jtf;
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                if (i == 0 ){
                    if(j == 0 || j == 6) {
                        jtf = new JTextField();
                        jtf.setVisible(false);
                        scacchiera.add(jtf);
                    } else {
                        tmp = g.getNORD(countN);
                        jtf = new JTextField();
                        jtf.setText(String.valueOf(tmp));
                        jtf.setFont(font1);
                        jtf.setHorizontalAlignment(JTextField.CENTER);
                        jtf.setBackground(Color.YELLOW);
                        jtf.setEditable(false);
                        jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        scacchiera.add(jtf);
                        countN++;
                    }
                } else if (i == 6){
                    if(j == 0 || j == 6) {
                        jtf = new JTextField();
                        jtf.setVisible(false);
                        scacchiera.add(jtf);
                    } else {
                        tmp = g.getSUD(countS);
                        jtf = new JTextField();
                        jtf.setText(String.valueOf(tmp));
                        jtf.setFont(font1);
                        jtf.setHorizontalAlignment(JTextField.CENTER);
                        jtf.setBackground(Color.YELLOW);
                        jtf.setEditable(false);
                        jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        scacchiera.add(jtf);
                        countS++;
                    }

                } else if(j==0){
                    tmp = g.getOVEST(countO);
                    jtf =new JTextField();
                    jtf.setText(String.valueOf(tmp));
                    jtf.setFont(font1);
                    jtf.setHorizontalAlignment(JTextField.CENTER);
                    jtf.setEditable(false);
                    jtf.setBackground(Color.YELLOW);
                    jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    scacchiera.add(jtf);
                    countO++;
                }else if(j==6){
                    tmp = g.getEST(countE);
                    jtf =new JTextField();
                    jtf.setText(String.valueOf(tmp));
                    jtf.setFont(font1);
                    jtf.setHorizontalAlignment(JTextField.CENTER);
                    jtf.setBackground(Color.YELLOW);
                    jtf.setEditable(false);
                    jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    scacchiera.add(jtf);
                    countE++;
                }
                else {
                    Cella c = g.getCella(i-1,j-1);
                    c.setController(this);
                    JTextField m = c.getMirror();
                    m.setText("");
                    m.setFont(font1);
                    m.setHorizontalAlignment(JTextField.CENTER);
                    m.setBackground(Color.ORANGE);
                    m.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    m.addActionListener(e -> {
                        int val;
                        try{
                            val = Integer.parseInt(m.getText());
                            if (val > 5 || val < 0) {
                                setDisplayMessage("Il valore immesso non è consentito. Usare numeri tra 1 e 5.");
                            }
                            else{
                                g.inserisci(val, c.getPosizione());
                                setDisplayMessage("Inserito il valore "+val+" in posizione+"+c.getPosizione().toString());
                            }
                        } catch(NumberFormatException nfe){
                            c.getControllore().setDisplayMessage("L'input non è un numero!");
                        }
                    });
                    scacchiera.add(m);
                }
            }
        }

        super.add(console, BorderLayout.NORTH);
        super.add(scacchiera, BorderLayout.CENTER);
        super.add(comandi, BorderLayout.SOUTH);

    }

    private void restart() {
        g = null;
        g = new Grattacielo(5,10);
    }

    public void setDisplayMessage(String s) {
        console.setText(s);
    }


    public Cella[] getCelle() {
        return celle;
    }

}
