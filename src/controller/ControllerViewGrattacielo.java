package controller;

import model.backtrack.Posizione;

import model.implProblema.Grattacielo;

import java.awt.*;
import java.awt.Font;
import javax.swing.*;

public class ControllerViewGrattacielo extends JPanel{
    private JPanel upperPage;
    private JTextField console;

    private JPanel middlePage;
    private JPanel north, south;
    private JPanel scacchiera, vincoliN, vincoliS, vincoliE, vincoliO;

    private JPanel lowerPage;
    private JPanel comandi;


    private Grattacielo g;
    private int numeroSoluzioni;
    private boolean risolto;
    private int[] soluzioneN;
    private int indiceN;
    private PosizioneJTF[] celle;


    public ControllerViewGrattacielo(Grattacielo g) {
        this.g = g;
        risolto = false;
        celle = new PosizioneJTF[g.getM().length];

        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        int tmp;
        JTextField jtf;

        //istanziamento zone della pagina
        upperPage = new JPanel();
        middlePage = new JPanel(new BorderLayout());
            north = new JPanel(new BorderLayout());
            south = new JPanel(new BorderLayout());
        lowerPage = new JPanel();

        //istanziamento contenuto UPPERPAGE
        console = new JTextField();
        console.setPreferredSize(new Dimension(400, 50));
        console.setEditable(false);
        console.setText("Console");
        console.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        upperPage.add(console);

        //istanziamento contenuto MIDDLEPAGE
        north.setPreferredSize(new Dimension(400,50));
        south.setPreferredSize(new Dimension(400,50));
        scacchiera = new JPanel(new GridLayout(5, 5));
        scacchiera.setPreferredSize(new Dimension(400,400));
        vincoliN = new JPanel(new GridLayout(1, 5));
        vincoliN.setPreferredSize(new Dimension(400,50));
        vincoliS = new JPanel(new GridLayout(1, 5));
        vincoliS.setPreferredSize(new Dimension(400,50));
        vincoliE = new JPanel(new GridLayout(5, 1));
        vincoliE.setPreferredSize(new Dimension(50,400));
        vincoliO = new JPanel(new GridLayout(5, 1));
        vincoliO.setPreferredSize(new Dimension(50,400));
        JPanel spacerNO = new JPanel();
        spacerNO.setPreferredSize(new Dimension(50,50));
        JPanel spacerNE = new JPanel();
        spacerNE.setPreferredSize(new Dimension(50,50));
        JPanel spacerSO = new JPanel();
        spacerSO.setPreferredSize(new Dimension(50,50));
        JPanel spacerSE = new JPanel();
        spacerSE.setPreferredSize(new Dimension(50,50));
        north.add(spacerNO, BorderLayout.LINE_START);
        north.add(vincoliN, BorderLayout.CENTER);
        north.add(spacerNE, BorderLayout.LINE_END);
        middlePage.add(north, BorderLayout.NORTH);
        for (int i = 0; i < 5; i++) {
            tmp = g.getNORD(i);
            jtf = new JTextField();
            jtf.setText(String.valueOf(tmp));
            jtf.setFont(font1);
            jtf.setHorizontalAlignment(JTextField.CENTER);
            jtf.setBackground(Color.YELLOW);
            jtf.setEditable(false);
            jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            vincoliN.add(jtf);
        }
        south.add(spacerSO, BorderLayout.LINE_START);
        south.add(vincoliS, BorderLayout.CENTER);
        south.add(spacerSE, BorderLayout.LINE_END);
        middlePage.add(south,BorderLayout.SOUTH);
        for (int i = 0; i < 5; i++) {
            tmp = g.getSUD(i);
            jtf = new JTextField();
            jtf.setText(String.valueOf(tmp));
            jtf.setFont(font1);
            jtf.setHorizontalAlignment(JTextField.CENTER);
            jtf.setBackground(Color.YELLOW);
            jtf.setEditable(false);
            jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jtf.setSize(new Dimension(70,70));
            vincoliS.add(jtf);
        }
        middlePage.add(vincoliE, BorderLayout.LINE_END);
        for (int i = 0; i < 5; i++) {
            tmp = g.getEST(i);
            jtf = new JTextField();
            jtf.setText(String.valueOf(tmp));
            jtf.setFont(font1);
            jtf.setHorizontalAlignment(JTextField.CENTER);
            jtf.setBackground(Color.YELLOW);
            jtf.setEditable(false);
            jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            vincoliE.add(jtf);
        }
        middlePage.add(vincoliO, BorderLayout.LINE_START);
        for (int i = 0; i < 5; i++) {
            tmp = g.getOVEST(i);
            jtf = new JTextField();
            jtf.setText(String.valueOf(tmp));
            jtf.setFont(font1);
            jtf.setHorizontalAlignment(JTextField.CENTER);
            jtf.setBackground(Color.YELLOW);
            jtf.setEditable(false);
            jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            vincoliO.add(jtf);
        }
        middlePage.add(scacchiera, BorderLayout.CENTER);


        //istanziamento contenuto LOWERPAGE
        comandi = new JPanel((new GridLayout(1, 4)));

        JButton indietro = new JButton("<-");
        indietro.addActionListener( e -> {
            if(!risolto){
                setDisplayMessage("Gioco non ancora risolto. Premere 'RISOLVI' per vedere le soluzioni possibili");
            } else {
               if(indiceN <= 0){
                   mostraSoluzione(indiceN);
               } else {
                   mostraSoluzione(indiceN-1);
                   indiceN--;
               }
            }
        });
        indietro.setMaximumSize(new Dimension(40,30));
        comandi.add(indietro);

        JButton controlla = new JButton("CONTROLLA");
        controlla.addActionListener(e -> {
            if (!g.controllaVincoli()) {
                setDisplayMessage("Soluzione non corretta.");
            } else {
                setDisplayMessage("Soluzione corretta!");
            }
        });
        controlla.setMaximumSize(new Dimension(100, 30));
        comandi.add(controlla);

        JButton risolvi = new JButton("RISOLVI");
        risolvi.addActionListener(e -> {
            g.risolvi(false);
            risolto = true;
            numeroSoluzioni = g.getSoluzioniTrovate();
            indiceN = 0;
            setDisplayMessage(("Ci sono "+numeroSoluzioni+"soluzioni. Per scorrerle usare i tasti <- e ->"));
            controlla.setEnabled(false);
            risolvi.setEnabled(false);
            mostraSoluzione(indiceN);
        });
        risolvi.setMaximumSize(new Dimension(100, 30));
        comandi.add(risolvi);

        JButton avanti = new JButton("->");
        avanti.addActionListener( e -> {
            if(!risolto){
                setDisplayMessage("Gioco non ancora risolto. Premere 'RISOLVI' per vedere le soluzioni possibili");
            } else {
                if(indiceN >= numeroSoluzioni-1){
                    mostraSoluzione(indiceN);
                } else {
                    mostraSoluzione(indiceN+1);
                    indiceN++;
                }
            }
        });
        avanti.setMaximumSize(new Dimension(40,30));
        comandi.add(avanti);
        lowerPage.add(comandi);
        lowerPage.setMaximumSize(new Dimension(500,50));


        //questo doppio ciclo "posiziona" ogni pedina al suo posto

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                PosizioneJTF pJ = new PosizioneJTF(new Posizione(i,j));
                celle[j+(i*5)] = pJ;
                pJ.setText("");
                pJ.setFont(font1);
                pJ.setHorizontalAlignment(JTextField.CENTER);
                pJ.setBackground(Color.ORANGE);
                pJ.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                int finalI = i, finalJ = j;
                JTextField finalJtf = pJ;
                pJ.addActionListener(e -> {
                    int val;
                    try {
                        val = Integer.parseInt(finalJtf.getText());
                        if (val > 5 || val < 0) {
                            setDisplayMessage("Il valore immesso non è consentito. Usare numeri tra 1 e 5.");
                            finalJtf.setText("");
                        }
                        else{
                            g.inserisci(val, new Posizione(finalI, finalJ));
                            setDisplayMessage("Inserito il valore "+val+" in posizione"+"("+finalI+","+finalJ+")");
                            g.stampa();
                        }
                    } catch (NumberFormatException nfe) {
                        setDisplayMessage("L'input non è un numero!");
                        finalJtf.setText("");
                    }
                });
                scacchiera.add(pJ);
            }
        }


        setLayout(new BorderLayout());
        add(upperPage, BorderLayout.NORTH);
        add(middlePage, BorderLayout.CENTER);
        add(lowerPage, BorderLayout.SOUTH);
    }

    public void setDisplayMessage(String s) {
        console.setText(s);
    }

    private void mostraSoluzione(int soluzione){
        soluzioneN = g.getSoluzioneN(soluzione);
        setDisplayMessage("Soluzione "+(soluzione+1)+"/"+numeroSoluzioni);
        for(int i=0; i<soluzioneN.length; i++){
            celle[i].setText(String.valueOf(soluzioneN[i]));
        }
    }



}
