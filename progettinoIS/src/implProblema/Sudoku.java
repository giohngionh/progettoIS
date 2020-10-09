package implProblema;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import backtrack.*;

public class Sudoku extends Problema<Posizione, Integer> {
	
	private int M[][];
	Map<Posizione, Integer> scelte = new HashMap<Posizione, Integer>();
	private LinkedList<Posizione> percorso = new LinkedList<Posizione>();
	private Posizione inizio, fine;
	private int minVal=1, maxVal;
	
	public Sudoku(int righe, int colonne,int soluzioni){
		super(soluzioni);
		if(righe!=colonne) throw new IllegalArgumentException("Righe e colonne non coincidono!");
		this.M=new int[righe][colonne];
		this.inizio=new Posizione(0,0);
		this.fine=new Posizione(righe-1, colonne-1);
		this.maxVal=righe;
	}/*costruttore che accetta il numero di soluzioni da trovare e imposta in automatico dimensione 
	   della scacchiera, i valori massimi su cui deve lavorare l'algoritmo e la le posizioni su cui 
	   ciclare */
	
	public Sudoku(int righe, int colonne){
		if(righe!=colonne) throw new IllegalArgumentException("Righe e colonne non coincidono!");
		this.M=new int[righe][colonne];
		this.inizio=new Posizione(0,0);
		this.fine=new Posizione(righe-1, colonne-1);
		this.maxVal=righe;
	}/*costruttore che imposta il massimo numero di soluzioni pari al massimo calcolabile. Per
		il resto identico al primo costr. */
	
	public Sudoku() {
		super(3);
		this.M=new int[5][5];
		this.inizio=new Posizione(0,0);
		this.fine=new Posizione(4, 4);
		this.maxVal=5;
	}//costruttore ad hoc per la mia applicazione, 5x5 con valori da 1 a 5. Cerca 3 soluzioni.
	
	@Override
	protected Posizione primoPuntoDiScelta() {
		return inizio;
	}

	@Override
	protected Posizione prossimoPuntoDiScelta(Posizione ps, Integer s) {
		int riga    = ps.getRiga();
		int colonna = ps.getColonna();
		if(colonna == riga && riga == M.length)
			return null;
		if(colonna < M.length-1)
			return new Posizione(riga, colonna+1);
		return new Posizione(riga+1, 0);
	}

	@Override
	protected Posizione ultimoPuntoDiScelta() {
		return fine;
	}

	@Override
	protected Integer primaScelta(Posizione ps) {
		return minVal;
	}

	@Override
	protected Integer prossimaScelta(Integer s) {
		return (s + 1) % (maxVal+1);
	}

	@Override
	protected Integer ultimaScelta(Posizione ps) {
		return maxVal;
	}

	@Override
	protected boolean assegnabile(Integer scelta, Posizione puntoDiScelta) {
		return sceltaUnica(scelta, puntoDiScelta);
	}
	
	protected boolean sceltaUnica(Integer s, Posizione p) {
		int riga = p.getRiga();
		int colonna = p.getColonna();
		for( int i = 0; i < M.length ; i++) {
			if(M[i][colonna] == s) return false;
		}
		for( int j = 0; j < M[0].length; j++) {
			if(M[riga][j] == s) return false;
		}
		return true;
	}//metodo per la verifica di unicita' del valore in una data riga e colonna

	@Override
	protected void assegna(Integer scelta, Posizione puntoDiScelta) {
		int riga = puntoDiScelta.getRiga();
		int colonna = puntoDiScelta.getColonna();
		percorso.add(puntoDiScelta);
		scelte.put(puntoDiScelta, scelta);
		M[riga][colonna]=scelta;
	}

	@Override
	protected void deassegna(Integer scelta, Posizione puntoDiScelta) {
		int riga = puntoDiScelta.getRiga();
		int colonna = puntoDiScelta.getColonna();
		if(!(puntoDiScelta.equals(percorso.getLast())))
			throw new IllegalArgumentException();
		percorso.removeLast();
		scelte.remove(puntoDiScelta);
		M[riga][colonna]=0;
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
	protected void scriviSoluzione(int nrsol) {
		System.out.println("Soluzione n: "+nrsol);
		for(int i=0; i<M.length; i++) {
			for(int j=0; j<M.length; j++) {
				System.out.print(M[i][j]+" ");
			}
			System.out.println();
		}
	}

}
