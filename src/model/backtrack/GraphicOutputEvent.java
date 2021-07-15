package model.backtrack;

public class GraphicOutputEvent implements GraphicEvent {
    private int[] source;

    public GraphicOutputEvent(int[] Matrice) {
        source = Matrice;
    }

    public int[] getSource(){
        return source;
    }

}
