package model.implProblema;

import model.backtrack.*;

import java.util.*;

public class Sudoku extends Problema<Posizione, Integer>{
    protected int[] M, Scacchiera;
    private Map<Posizione, Integer> scelte;
    private LinkedList<Posizione> percorso;
    private HashSet<Posizione> scelteUtente;
    private Posizione inizio, fine;
    private int minVal= 1, maxVal, soluzioneCasuale, N;



    public Sudoku(int n,int soluzioni){
        super(soluzioni);
        this.M=new int[n*n];
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                M[(i*n)+j] = 0;
            }
        }
        this.N=n;
        this.inizio=new Posizione(0,0);
        this.fine=new Posizione(n-1,n-1);
        this.maxVal= n;
        scelte = new HashMap<Posizione, Integer>();
        percorso = new LinkedList<Posizione>();
        scelteUtente = new HashSet<Posizione>();
		soluzioneCasuale=(int) (Math.random()*(161280-2))+1;
    }/*costruttore che accetta il numero di soluzioni da trovare e imposta in automatico dimensione
	   della scacchiera, i valori massimi su cui deve lavorare l'algoritmo e le posizioni su cui
	   ciclare */

    public Sudoku(int n) {
        super();
        this.M=new int[n*n];
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                M[(i*n)+j] = 0;
            }
        }
        this.N=n;
        this.inizio=new Posizione(0,0);
        this.fine=new Posizione(n-1,n-1);
        this.maxVal=Integer.valueOf(n);
        scelte = new HashMap<Posizione, Integer>();
        percorso = new LinkedList<Posizione>();
        scelteUtente = new HashSet<Posizione>();
        soluzioneCasuale = (int) (Math.random()*(161280))+1;
    }//costruttore ad hoc per la mia applicazione

    private void setInizio(Posizione custom) {
        this.inizio=custom;
    }

    private void setFine(Posizione custom){
        this.fine=custom;
    }

    @Override
    protected Posizione primoPuntoDiScelta() {
        if(scelteUtente.contains(inizio)) {
            Posizione inizioEff = nextPuntoDiScelta(inizio,1);
            while(scelteUtente.contains(inizioEff))
                inizioEff = nextPuntoDiScelta(inizioEff,1);
            setInizio(inizioEff);
        }
        return inizio;
    }

    @Override
    protected Posizione ultimoPuntoDiScelta() {
        if(scelteUtente.contains(fine)) {
            Posizione fineEff = prevPuntoDiScelta(fine,1);
            while(scelteUtente.contains(fineEff))
                fineEff = prevPuntoDiScelta(fineEff,1);
            setFine(fineEff);
        }
        return fine;
    }

    @Override
    protected Posizione nextPuntoDiScelta(Posizione ps, Integer s) {
        int r = ps.getRiga();
        int c = ps.getColonna();
        if(c == N-1 && r ==N-1) return null;
        if(c == N-1)
            return new Posizione(r+1,0);
        return new Posizione(r,c+1);
    }

    protected Posizione prevPuntoDiScelta(Posizione ps, Integer s) {
        int r = ps.getRiga();
        int c = ps.getColonna();
        if (c == 0 && r == 0) return null;
        if (c == 0)
            return new Posizione(r - 1, N - 1);
        return new Posizione(r, c - 1);
    }

    @Override
    protected Integer primaScelta(Posizione ps) {
        return minVal;
    }

    @Override
    protected Integer prossimaScelta(Integer s) {
        return (s + 1) % (maxVal + 1);
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
        int pos = p.getColonna()+(p.getRiga()*N);
        int i=pos, N=maxVal;
        while(i%N!=0 || i%N==N) {
            i--;
            if(M[i]==s)
                return false;
        }//controllo la riga corrente per duplicati andando dalla pos corrente a inizio riga
        i=pos;
        while(i%N!=N-1) {
            i++;
            if(M[i]==s)
                return false;
        }//controllo la riga corrente per duplicati andando dalla pos corrente a fine riga
        i=pos;
        while(i>N) {
            i-=N;
            if(M[i]==s)
                return false;
        }//controllo la collonna corrente andando dalla pos corrente a inizio colonna
        i=pos;
        while(i<N*(N-1)) {
            i+=N;
            if(M[i]==s)
                return false;
        }//controllo la collonna corrente andando dalla pos corrente a inizio colonna
        return true;
    }//metodo per la verifica di unicita' del valore in una data riga e colonna

    protected boolean verificaVincoli(){
        return true;
    }

    @Override
    protected void assegna(Integer scelta, Posizione puntoDiScelta) {
        if(scelteUtente.contains(puntoDiScelta))
            return;

        int r=puntoDiScelta.getRiga();
        int c=puntoDiScelta.getColonna();
        percorso.add(puntoDiScelta);
        scelte.put(puntoDiScelta, scelta);
        M[c+(r*N)] = scelta;
    }

    @Override
    protected void deassegna(Integer scelta, Posizione puntoDiScelta) {
        if(!(puntoDiScelta.equals(percorso.getLast())))
            throw new IllegalArgumentException();
        int r=puntoDiScelta.getRiga();
        int c=puntoDiScelta.getColonna();
        percorso.removeLast();
        scelte.remove(puntoDiScelta);
        M[c+(r*N)] = 0;
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
        if(verificaVincoli()) {
            System.out.println("Soluzione numero:"+nrsol);
            for (int i = 0; i < M.length; i++) {
                if((i)%maxVal==0 && i!=0) {System.out.println();}
                System.out.print(M[i]+" ");
            }
            System.out.println("\n");
        }
    }

    public void inserisci(int valore, Posizione ps) {
        if(scelteUtente.contains(ps)) {
            scelteUtente.remove(ps);
        }
        int r=ps.getRiga();
        int c=ps.getColonna();
        M[c+(r*N)] = valore;
        scelteUtente.add(ps);
    }//inserisci

    public void rimuovi(Posizione ps) {
        if(!scelteUtente.contains(ps))
            throw new IllegalArgumentException("La casella che si cerca di rimuovere non esiste!");
        int r=ps.getRiga();
        int c=ps.getColonna();
        M[c+(r*N)] = 0;
        scelteUtente.remove(ps);
    }

    protected void salva(int numeroSoluzione){
        if(numeroSoluzione != soluzioneCasuale)
            return;
        else{
            this.Scacchiera = new int[M.length];
            for (int i = 0; i < M.length; i++){
                Scacchiera[i] = M[i];
            }
        }
    }

    protected int[] getScacchiera(){
        return Scacchiera;
    }

    public int[] getM(){
        return M;
    }

    protected int getN(){
        return N;
    }





    //testingggggg
    public void stampa() {
        scriviSoluzione(-1);
    }

}
