package model.backtrack;

public class Posizione {
    private int riga, colonna;

    public Posizione(int riga, int colonna) {
        this.riga=riga;
        this.colonna=colonna;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna(){
        return colonna;
    }

    @Override
    public int hashCode(){
        return riga*colonna*37;
    }

    @Override
    public boolean equals(Object o) {
        if( o == null )
            return false;
        if( o == this )
            return true;
        if(o.getClass()!=this.getClass())
            return false;
        Posizione tmp=(Posizione) o;
        return this.riga==tmp.riga & this.colonna==tmp.colonna;
    }

    @Override
    public String toString() {
        return "("+riga+","+colonna+")";
    }

}
