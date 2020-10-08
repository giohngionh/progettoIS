package backtrack;

public class Posizione {

	private int riga, colonna;
	
	public Posizione(int r, int c) {
		this.riga=r;
		this.colonna=c;
	}

	public int getRiga() {
		return riga;
	}

	public int getColonna() {
		return colonna;
	}
	
	@Override
	public int hashCode(){
		final int nPrimo = 37;
		int res=1;
		res = nPrimo * res + colonna;
		res = nPrimo * res + riga;
		return res;
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
		return tmp.colonna==this.colonna && tmp.riga==this.colonna;
	}
	
	@Override
	public String toString() {
		return "("+riga+","+colonna+")";
	}
}
