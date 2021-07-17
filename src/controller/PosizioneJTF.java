package controller;

import model.backtrack.Posizione;

import javax.swing.*;

public class PosizioneJTF extends JTextField {
    private Posizione p;

    public PosizioneJTF(Posizione p){
        super();
        this.p = p;
    }

    public Posizione getPosizione(){
        return p;
    }
}
