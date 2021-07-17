package model.implProblema;

import java.util.LinkedList;

public class Grattacielo extends Sudoku {

    private int[] NORD, SUD, OVEST, EST;
    private LinkedList<int[]> soluzioniTrovate;
    private int maxSoluzioni, solCorrente=0;

    public Grattacielo(int []n, int []s, int []e, int []o, int maxSoluzioni) {
        super(n.length);
        if(n.length!=s.length) throw new IllegalArgumentException("I vincoli devono avere tutti egual dimensione");
        if(s.length!=e.length) throw new IllegalArgumentException("I vincoli devono avere tutti egual dimensione");
        if(e.length!=o.length) throw new IllegalArgumentException("I vincoli devono avere tutti egual dimensione");
        this.maxSoluzioni=maxSoluzioni;
        this.NORD=new int[n.length];
        this.SUD=new int[n.length];
        this.EST=new int[n.length];
        this.OVEST=new int[n.length];
        for (int i = 0; i < NORD.length; i++){
            this.NORD[i]=n[i];
            this.SUD[i]=s[i];
            this.EST[i]=e[i];
            this.OVEST[i]=o[i];
        }
    }/*	costruttore che determina la dimensione della scacchiera basandosi sul
		vincolo Nord, setta i vincoli del problema prendendoli come input e
	    setta il numero massimo di soluzioni*/

    public Grattacielo(int n, int maxSoluzioni) {
        super(n);
        this.maxSoluzioni=maxSoluzioni;
        this.NORD=new int[n];
        this.SUD=new int[n];
        this.EST=new int[n];
        this.OVEST=new int[n];
        soluzioniTrovate = new LinkedList<>();
        estraiVincoli();
    }/*	costruttore che accetta la dimensione della scacchiera, genera i vincoli
		e accetta il numero massimo di soluzioni*/

    private void estraiVincoli() {
        super.risolvi(true);
        int[] s=super.getScacchiera();
        
        int count=0, N=getN(), max=-1;
        //calcolo vincoli NORD
        for(int i=0; i<N ; i++){
            for ( int j=i; j<(N*N); j+=N){
                if(s[j]>max){
                    count++;
                    max=s[j];
                }
            }
            NORD[i]=count;
            count=0; max=-1;
        }
        //calcolo vincoli SUD
        int x=0;
        for(int i=s.length-5; i<s.length ; i++){
            for ( int j=i; j>0; j-=N){
                if(s[j]>max){
                    count++;
                    max=s[j];
                }
            }
            SUD[x]=count;
            count=0; max=-1; x++;
        }
        x=0;
        //calcolo vincoli OVEST
        for(int i=0; i<s.length; i++){
            if(s[i]>max){
                count++;
                max=s[i];
            }
            if((i+1)%N==0) { //siamo in corrisponde della fine di una riga della scacchiera
                OVEST[x]=count;
                count=0; max=-1;
                x++;
            }
        }
        x=N-1;
        //calcolo vincoli EST
        for(int i=s.length-1; i>=0; i--){
            if(s[i]>max){
                count++;
                max=s[i];
            }
            if(i%N==0) { //siamo in corrisponde dell'inizio di una riga della scacchiera
                EST[x]=count;
                count=0; max=-1;
                x--;
            }
        }
    }

    @Override
    protected boolean verificaVincoli() {
        int[] M=getM();
        int count=0, N=getN(), max=-1;
        //verifico vincoli NORD
        for(int i=0; i<N ; i++){
            for ( int j=i; j<(N*N); j+=N){
                if(M[j]>max){
                    count++;
                    max=M[j];
                }
            }
            if(NORD[i]!=count)
                return false;
            count=0; max=-1;
        }
        //verifico vincoli SUD
        int x=0;
        for(int i=M.length-5; i<M.length-1 ; i++){
            for ( int j=i; j>0; j-=N){
                if(M[j]>max){
                    count++;
                    max=M[j];
                }
            }
            if(SUD[x]!=count)
                return false;
            count=0; max=-1; x++;
        }
        x=0;
        //verifico vincoli OVEST
        for(int i=0; i<M.length; i++){
            if(M[i]>max){
                count++;
                max=M[i];
            }
            if((i+1)%N==0) { //siamo in corrisponde della fine di una riga della scacchiera
                if(OVEST[x]!=count)
                    return false;
                count=0; max=-1;
                x++;
            }
        }
        x=N-1;
        //verifico vincoli EST
        for(int i=M.length-1; i>=0; i--){
            if(M[i]>max){
                count++;
                max=M[i];
            }
            if((i)%N==0) { //siamo in corrisponde dell'inizio di una riga della scacchiera
                if(EST[x]!=count)
                    return false;
                count=0; max=-1;
                x--;
            }
        }
        return true;
    }

    public boolean controllaVincoli(){
        return verificaVincoli();
    }

    @Override
    protected void scriviSoluzione(int nrsol) {
        if(verificaVincoli()){
            if(solCorrente<maxSoluzioni) {
                solCorrente++;
                System.out.println("Ho trovato una soluzione. Le soluzioni sono attualmente " +solCorrente);
                int[] tmp = new int[getN()*getN()];
                for(int i = 0; i<tmp.length; i++){
                    tmp[i]=M[i];
                }
                soluzioniTrovate.add(tmp);
            }
        }
        /*  if(verificaVincoli()){
            if(solCorrente<maxSoluzioni){
                solCorrente++;
                int[] M=getM();
                System.out.println("Soluzione conforme numero: " + solCorrente);
                notifyListeners(new GraphicOutputEvent(this.M));
                System.out.print("  ");
                for (int i = 0; i < getN(); i++) {
                    if (i != getN() - 1)
                        System.out.print(NORD[i] + " ");
                    else
                        System.out.print(NORD[i]);
                }
                System.out.print("  ");
                System.out.println();

                for (int i = 0; i < (NORD.length * 2) + 4; i++)
                    System.out.print("-");
                System.out.println();
                //sezione nord

                for (int i = 0; i < M.length; i+=getN()) {
                    System.out.print(OVEST[i/getN()] + "|");
                    for (int j = i; j < i+NORD.length; j++) {
                        if (j+1%(NORD.length)!=0)
                            System.out.print(M[j] + " ");
                        else
                            System.out.print(M[j]);
                    }
                    System.out.print("|" + EST[i/getN()]);
                    System.out.println();
                }
                //blocco principale compreso EST e OVEST

                for (int i = 0; i < (SUD.length * 2) + 4; i++)
                    System.out.print("-");

                System.out.println();
                System.out.print("  ");
                for (int i = 0; i < SUD.length; i++) {
                    if (i != SUD.length - 1)
                        System.out.print(SUD[i] + " ");
                    else
                        System.out.print(SUD[i]);
                }
                System.out.print("  ");
                System.out.println();
            }

        }
*/
    }

    public void stampa(){
        int[] M=getM();

        System.out.print("  ");
        for (int i = 0; i < getN(); i++) {
            if (i != getN() - 1)
                System.out.print(NORD[i] + " ");
            else
                System.out.print(NORD[i]);
        }
        System.out.print("  ");
        System.out.println();

        for (int i = 0; i < (NORD.length * 2) + 4; i++)
            System.out.print("-");
        System.out.println();
        //sezione nord

        for (int i = 0; i < M.length; i+=getN()) {
            System.out.print(OVEST[i/getN()] + "|");
            for (int j = i; j < i+NORD.length; j++) {
                if (j+1%(NORD.length)!=0)
                    System.out.print(M[j] + " ");
                else
                    System.out.print(M[j]);
            }
            System.out.print("|" + EST[i/getN()]);
            System.out.println();
        }
        //blocco principale compreso EST e OVEST

        for (int i = 0; i < (SUD.length * 2) + 4; i++)
            System.out.print("-");

        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < SUD.length; i++) {
            if (i != SUD.length - 1)
                System.out.print(SUD[i] + " ");
            else
                System.out.print(SUD[i]);
        }
        System.out.print("  ");
        System.out.println();
    }

    public void stampa(int indice){
        int[] M=soluzioniTrovate.get(indice);

        System.out.print("  ");
        for (int i = 0; i < getN(); i++) {
            if (i != getN() - 1)
                System.out.print(NORD[i] + " ");
            else
                System.out.print(NORD[i]);
        }
        System.out.print("  ");
        System.out.println();

        for (int i = 0; i < (NORD.length * 2) + 4; i++)
            System.out.print("-");
        System.out.println();
        //sezione nord

        for (int i = 0; i < M.length; i+=getN()) {
            System.out.print(OVEST[i/getN()] + "|");
            for (int j = i; j < i+NORD.length; j++) {
                if (j+1%(NORD.length)!=0)
                    System.out.print(M[j] + " ");
                else
                    System.out.print(M[j]);
            }
            System.out.print("|" + EST[i/getN()]);
            System.out.println();
        }
        //blocco principale compreso EST e OVEST

        for (int i = 0; i < (SUD.length * 2) + 4; i++)
            System.out.print("-");

        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < SUD.length; i++) {
            if (i != SUD.length - 1)
                System.out.print(SUD[i] + " ");
            else
                System.out.print(SUD[i]);
        }
        System.out.print("  ");
        System.out.println();
    }

    public int getNORD(int indice){
        return NORD[indice];
    }

    public int getSUD(int indice){
        return SUD[indice];
    }

    public int getOVEST(int indice){
        return OVEST[indice];
    }

    public int getEST(int indice){
        return EST[indice];
    }

    public int[] getSoluzioneN(int n){
        return soluzioniTrovate.get(n);
    }

    public int getSoluzioniTrovate(){
        return solCorrente;
    }

    public int getCella(int r, int c){
        return M[c+(r*getN())];
    }

}

