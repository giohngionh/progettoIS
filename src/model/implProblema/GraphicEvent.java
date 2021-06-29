package model.implProblema;

public class GraphicEvent {
    private GraphicObject source;

    public GraphicEvent(GraphicObject src){
        source=src;
    }
    public GraphicObject getSource() {
        return source;
    }
}
