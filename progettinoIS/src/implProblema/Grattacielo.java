package implProblema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import backtrack.*;

public class Grattacielo extends Problema<Posizione, Integer> {
	
	private int M[][];
	private int [][] matrice;
	private Map<Posizione, Integer> scelte = new HashMap<Posizione, Integer>();
	private LinkedList<Posizione> percorso = new LinkedList<Posizione>();
	private HashSet<Posizione> userChoices=new HashSet<>();
	private Posizione inizio, fine;
	private int minVal=1, maxVal;
	private int[] NORD, SUD, OVEST, EST;
	private int soluzioniValide=0, soluzioniMAX=0;
	
	public Grattacielo(int N, int []n, int []s, int []e, int []o) {
		this.soluzioniMAX=Integer.MAX_VALUE;
		this.M=new int[N][N];
		this.inizio=new Posizione(0,0);
		this.fine=new Posizione(N-1,N-1);
		this.maxVal=N;
		this.NORD=n;
		this.SUD=s;
		this.EST=e;
		this.OVEST=o;
	}
	
	public Grattacielo(int n, int maxSol) {
		this.soluzioniMAX=maxSol;
		this.M=new int [n][n];
		this.inizio=new Posizione(0,0);
		this.fine=new Posizione(n-1,n-1);
		this.maxVal=n;
		this.NORD=new int[n];
		this.SUD=new int[n];
		this.EST=new int[n];
		this.OVEST=new int[n];
		generaVincoli(n);
	}
	
	@Override
	protected Posizione primoPuntoDiScelta() {
		return inizio;
	}

	//fatto
	
	protected Posizione prossimoPuntoDiScelta(Posizione ps, Integer v) {
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
	protected Integer prossimaScelta(Integer v) {
		return (v + 1) % (maxVal+1);
	}
	
	@Override
	protected Integer ultimaScelta(Posizione ps) {
		return maxVal;
	}
	
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
	
	protected boolean verificaVincoli() {
		int count=1, max;
		for(int j=0; j<M.length; j++) {
			//sceglie colonna da controllare 
			int i=0;
			max=M[i][j]; 
			while(i<M.length-1) {
				if(M[i+1][j] > max) {
					count++;
					max=M[i+1][j];
					i++;
				}
				else
					i++;
			}
			//ho determinato quanti palazzi si "vedono" nella colonna j guardando da NORD
			if(count!=NORD[j]) return false;
			count=1;
			
			i=M.length-1;
			max=M[i][j]; 
			while(i>0) {
				if(M[i-1][j] > max) {
					count++;
					max=M[i-1][j];
					i--;
				}
				else
					i--;
			}
			//ho determinato quanti palazzi si "vedono" dalla colonna j guardando da SUD
			if(count!=SUD[j]) return false;
			count=1;
		}//CONTROLLO NORD + SUD
		count=1;
		for(int i=0; i<M.length; i++) {
			int j=0;
			max=M[i][j]; 
			while(j<M.length-1) {
				if(M[i][j+1] > max) {
					count++;
					max=M[i][j+1];
					j++;
				}
				else
					j++;
			}
			//ho determinato quanti palazzi si "vedono" dalla riga i guardando da OVEST
			if(count!=OVEST[i]) return false;
			count=1;
			
			j=M.length-1;
			max=M[i][j];
			while(j>0) {
				if(M[i][j-1] > max) {
					count++;
					max=M[i][j-1];
					j--;
				}
				else
					j--;
			}
			//ho determinato quanti palazzi si "vedono" dalla colonna j guardando da EST
			if(count!=EST[i]) return false;
			count=1;
		}//CONTROLLO OVEST +SUD
		return true;
	}
	
	private void generaVincoli(int n) {
		Sudoku sudoku = new Sudoku(n);
		sudoku.risolvi();
		this.matrice = sudoku.ritornaSoluzione();
		assegnaVincoli(matrice);
	}
	
	private void assegnaVincoli(int [][] m) {
		int count=1, max;
		for(int j=0; j<matrice.length; j++) {
			int i=0;
			max=matrice[i][j]; 
			while(i<matrice.length-1) {
				if(matrice[i+1][j] > max) {
					count++;
					max=matrice[i+1][j];
					i++;
				}
				else
					i++;
			}
			//ho determatriceinato quanti palazzi si "vedono" nella colonna j guardando da NORD
			NORD[j]=count;
			count=1;
			
			i=matrice.length-1;
			max=matrice[i][j]; 
			while(i>0) {
				if(matrice[i-1][j] > max) {
					count++;
					max=matrice[i-1][j];
					i--;
				}
				else
					i--;
			}
			//ho determatriceinato quanti palazzi si "vedono" dalla colonna j guardando da SUD
			SUD[j]=count;
			count=1;
		}//CONTROLLO NORD + SUD
		
		for(int i=0; i<matrice.length; i++) {
			int j=0;
			max=matrice[i][j]; 
			while(j<matrice.length-1) {
				if(matrice[i][j+1] > max) {
					count++;
					max=matrice[i][j+1];
					j++;
				}
				else
					j++;
			}
			//ho determatriceinato quanti palazzi si "vedono" dalla riga i guardando da OVEST
			OVEST[i]=count;
			count=1;
			
			j=matrice.length-1;
			max=matrice[i][j]; 
			while(j>0) {
				if(matrice[i][j-1] > max) {
					count++;
					max=matrice[i][j-1];
					j--;
				}
				else
					j--;
			}
			//ho determinato quanti palazzi si "vedono" dalla colonna j guardando da EST
			EST[i]=count;
			count=1;
		}//CONTROLLO OVEST +SUD
	}
	
	@Override
	protected void assegna(Integer value, Posizione puntoDiScelta) {
		if(userChoices.contains(puntoDiScelta)) {
			return;
		}
		percorso.add(puntoDiScelta);
		scelte.put(puntoDiScelta, value);
		M[puntoDiScelta.getRiga()][puntoDiScelta.getColonna()]=value;
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
	protected void scriviSoluzione(int nrsol) {
		if(nrsol==-1) {
			System.out.println("La configurazione attuale di vincoli non ammette soluzioni.");
		}
		if(verificaVincoli() && this.soluzioniValide<this.soluzioniMAX) {
			this.soluzioniValide++;
			System.out.println("Soluzione numero: "+soluzioniValide);
			System.out.print("  ");
			for(int i=0; i<M.length; i++) {
				if(i!=M.length-1)
					System.out.print(NORD[i]+" ");
				else
					System.out.print(NORD[i]);
			}
			System.out.print("  ");
			System.out.println();
			
			for(int i=0; i<(M.length*2)+4; i++) 
				System.out.print("-");
			System.out.println();
			//sezione nord
			
			for(int i=0; i<M.length; i++) {
				System.out.print(OVEST[i]+"|");
				for(int j=0; j<M[0].length; j++) {
					if(j!=M[0].length-1)
						System.out.print(M[i][j]+" ");
					else
						System.out.print(M[i][j]);
				}
				System.out.print("|"+EST[i]);
				System.out.println();
			}
			//blocco principale compreso EST e OVEST
			
			for(int i=0; i<(M.length*2)+4; i++) 
				System.out.print("-");
			
			System.out.println();
			System.out.print("  ");
			for(int i=0; i<M.length; i++) {
				if(i!=M.length-1)
					System.out.print(SUD[i]+" ");
				else
					System.out.print(SUD[i]);
			}
			System.out.print("  ");
			System.out.println();
		}
		
	}
	
	protected void stampa(int [][] m){
		for (int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				if(j!=m.length-1)	
					System.out.print(m[i][j]+" ");
				else
					System.out.print(m[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	protected void stampaVincoli(int[]v) {
		
		for(int j=0; j<v.length; j++) {
			if(j!=v.length-1)	
				System.out.print(v[j]+" ");
			else
				System.out.print(v[j]);
		}
		System.out.print("\n");
	}
	
	public void inserisci(int x, Posizione p) {
		userChoices.add(p);
		assegna(x,p);
	}
	
	public void rimuovi(int x,Posizione p) {
		userChoices.remove(p);
		deassegna(x,p);
	}
}
