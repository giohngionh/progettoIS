package prove;

import backtrack.Posizione;

public class TestPosizione {
	public static void main(String[] args) {
		Posizione p1=new Posizione(0,4);
		Posizione p2=new Posizione(4,4);
		if(p1.equals(p2))
			System.out.println("i due punti coincidono");
	}
}
