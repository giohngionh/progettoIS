package model.implProblema;

import model.backtrack.Posizione;



public interface GraphicObject {

    void addGraphicObjectListener(GraphicObjectListener l);

    void removeGraphicObjectListener(GraphicObjectListener l);

    Posizione getPosizione();

    String getType();
}
