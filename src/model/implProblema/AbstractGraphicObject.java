package model.implProblema;

import model.backtrack.Posizione;

import java.util.*;

public class AbstractGraphicObject implements GraphicObject{
    private List<GraphicObjectListener> listeners = new LinkedList<>();

    @Override
    public void addGraphicObjectListener(GraphicObjectListener l) {
        if (listeners.contains(l))
            return;
        listeners.add(l);

    }

    @Override
    public void removeGraphicObjectListener(GraphicObjectListener l) {
        listeners.remove(l);

    }

    protected void notifyListeners(GraphicEvent e) {

        for (GraphicObjectListener gol : listeners)

            gol.graphicChanged(e);

    }

    @Override
    public Posizione getPosizione() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

}
