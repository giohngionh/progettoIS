package implProblema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import backtrack.*;

public class Grattacielo extends Problema<Posizione, Integer> {
	
	private int M[][];
	
	Map<Posizione, Integer> scelte = new HashMap<Posizione, Integer>();
	
	private LinkedList<Posizione> percorso = new LinkedList<Posizione>();
	
	private Posizione inizio, fine;
	
	private boolean righePoiColonne;
	
	public Grattacielo(int m[][],boolean righePoiColonne) {
		this.M=m;
		this.righePoiColonne=righePoiColonne;
		this.inizio=new Posizione(1,1);
		this.fine=new Posizione(5,5);
	}
	
	@Override
	protected Posizione primoPuntoDiScelta() {
		return inizio;
	}
	//fatto

	@Override
	protected Integer primaScelta(Posizione ps) {
		return Integer.values()[0];
	}
	//fatto
	
	@Override
	protected Integer prossimaScelta(Integer v) {
		return v.next();
	}
	//fatto
	
	@Override
	protected Integer ultimaScelta(Posizione ps) {
		return Integer.values()[Integer.values().length-1];
	}
	//fatto
	
	protected Posizione prossimoPuntoDiScelta(Posizione ps, Integer v) {
		int riga=ps.getRiga();
		int col=ps.getColonna();
		Posizione p;
		if(riga<5) {
			if(col<5) {
				p=new Posizione(riga, col+1);
				return p;
			}
			p=new Posizione(riga+1, 1);
			return p;
		}
		if(col<5) {
			p=new Posizione(riga, col+1);
			return p;
		}
		return null;
	}
	
	@Override
	protected boolean assegnabile(Integer value, Posizione puntoDiScelta) {
		int riga=puntoDiScelta.getRiga();
		int colonna=puntoDiScelta.getColonna();
		if(unicita(riga, colonna, value) && visibilita(riga, colonna))
			return true;
		return false;		
	}
	//fatto

	private boolean unicita(int riga, int colonna, Integer v) {
		int val=v.val();
		int i=1;
		while(i<6) {
			if(i==riga) i++;
			if(M[i][colonna]==val)	return false;
		}
		i=1;
		while(i<6) {
			if(i==colonna) i++;
			if(M[riga][i]==val) return false;
		}
		return true;
	}
	//fatto
	
	private boolean visibilita(int riga, int colonna) {
		int contN=0, contS=0, contE=0, contO=0;
		//controllo che i valori rispettino il vincolo ovest
		int tmp=0;
		int i=1;
		while(i<6) {
			if(M[riga][i]>tmp) {
				tmp=M[riga][i];
				contO++;
			}
			i++;
		}
		if (contO!=M[riga][0]) return false;
		//controllo che i valori rispettino il vincolo est
		tmp=0;
		i=5;
		while(i>0) {
			if(M[riga][i]>tmp) {
				tmp=M[riga][i];
				contE++;
			}
			i--;
		}
		if (contE!=M[riga][6]) return false;
		//controllo che i valori rispettino il vincolo nord
		tmp=0;
		i=1;
		while(i<6) {
			if(M[i][colonna]>tmp) {
				tmp=M[i][colonna];
				contN++;
			}
			i++;
		}
		if(contS!=M[0][colonna]) return true;
		//controllo che i valori rispettino il vincolo sud
		tmp=0;
		i=5;
		while(i>0) {
			if(M[i][colonna]>tmp) {
				tmp=M[i][colonna];
				contS++;
			}
			i--;
		}
		if(contS!=M[6][colonna]) return true;
		return true;
	}
	//fatto
	
	@Override
	protected void assegna(Integer value, Posizione puntoDiScelta) {
		percorso.add(puntoDiScelta);
		scelte.put(puntoDiScelta, value);
		M[puntoDiScelta.getRiga()][puntoDiScelta.getColonna()]=value.val();
	}

	@Override
	protected void deassegna(Integer value, Posizione puntoDiScelta) {
		if (!puntoDiScelta.equals(percorso.getLast()))
			throw new IllegalArgumentException();
		percorso.removeLast();
		scelte.remove(puntoDiScelta);
		M[puntoDiScelta.getRiga()][puntoDiScelta.getColonna()]=0;
	}

	@Override
	protected Posizione precedentePuntoDiScelta(Posizione puntoDiScelta) {
		return percorso.getLast();
	}

	@Override
	protected Integer ultimaSceltaAssegnata(Posizione puntoDiScelta) {
		return scelte.get(puntoDiScelta);
	}

	@Override
	protected Posizione ultimoPuntoDiScelta() {
		return fine;
	}

	@Override
	protected void scriviSoluzione(int nrsol) {
		System.out.println("Soluzione numero: "+nrsol);
		System.out.println(this.toString());
	}
	
	public String print(){
		StringBuilder sb=new StringBuilder(600);
		for (int i=0; i<=6; i++) {
			for(int j=0; j<=6; j++) {
				if(M[i][j]==-1)
					sb.append("#");
				else
					sb.append(M[i][j]);
				if(j!=6) sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
