package model.implProblema;

import javax.swing.*;

import controller.ControllerGrattacielo;
import model.backtrack.Posizione;

public class Cella extends AbstractGraphicObject {

    private Posizione posizione;
    private int valore;
    private ControllerGrattacielo controllore;
    private JTextField mirror;

    public Cella(Posizione p, int v, ControllerGrattacielo cg) {
        this.posizione = p;
        this.valore = v;
        this.controllore = cg;
        this.mirror = new JTextField();
    }

    public void setValore(int val) {
        valore = val;
       notifyListeners(new GraphicEvent(this));
        setMirror(val);
    }

    public int getValore(){
        return valore;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setController(ControllerGrattacielo cg){
        this.controllore = cg;
    }

    public ControllerGrattacielo getControllore(){
        return controllore;
    }

    public void setMirror(int i){
        mirror.setText(String.valueOf(i));
        mirror.validate();
    }

    public JTextField getMirror(){
        return mirror;
    }
}

