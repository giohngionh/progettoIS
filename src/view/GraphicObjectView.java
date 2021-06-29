package view;


import model.implProblema.Cella;
import model.implProblema.GraphicEvent;
import model.implProblema.GraphicObjectListener;

import javax.swing.*;

public class GraphicObjectView implements GraphicObjectListener {
    @Override
    public void graphicChanged(GraphicEvent e) {
        Cella c = (Cella) e.getSource();

    }

}
