package model.prove;

import java.util.Scanner;

import model.backtrack.Posizione;
import model.implProblema.Grattacielo;

public class TestGrattacielo {
    public static void main(String[] args) {
//        int[] n = {2, 1, 3, 2, 5};
//        int[] s = {2, 2, 2, 4, 1};
//        int[] e = {3, 2, 3, 2, 1};
//        int[] o = {2, 3, 3, 1, 3};
//        Grattacielo g = new Grattacielo(n, s, e, o, 3);
//        g.risolvi();
        /* Il costruttore manuale funziona e risolve la scacchiera data. Ora provo con il costruttore automatico */
        Grattacielo g1 = new Grattacielo(5, 3);
        /* Il costruttore automatico funziona e risolve la scacchiera sempre. Oro provo con una partita a riga di comando   */
      Scanner sc= new Scanner(System.in);
        while(true) {
            g1.stampa();
            System.out.println("Inserire valore oppure 99 per risolvere");
            int val=sc.nextInt();
            if(val==99) {
                g1.risolvi(false);
                System.out.println("Indica quale soluzione vuoi vedere da 1 a "+g1.getSoluzioniTrovate());
                System.out.println("Oppure inserisci 99 per uscire");
                val = sc.nextInt();
                while (val != 99){
                    g1.stampa(val-1);
                    val = sc.nextInt();
                }
                return;
            }
            System.out.println("Inserire riga");
            int i=sc.nextInt();
            System.out.println("Inserire colonna");
            int j=sc.nextInt();
            g1.inserisci(val,new Posizione(i,j));
            g1.stampa();
            if(g1.controllaVincoli()) {
                System.out.println("solizione corretta!");
                break;
            }
        }

    }

}
