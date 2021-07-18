package testing;

import model.backtrack.Posizione;
import model.implProblema.Grattacielo;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.Assert.assertEquals;

public class GrattacieloTest {

    @Test
    public void valori_numerici_consentitiEnon(){
        Grattacielo g = new Grattacielo(5,5);
        for(int valore=-10000; valore<10000; valore++){
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    g.inserisci(valore,new Posizione(i,j));
                }
            }
            boolean flag = g.controllaVincoli();
            assertEquals(false,flag);
        }
    }//il test prova possibili input numerici, anche non ammissibili. La logica di controllo sugli input è implementata
    //ad un livello più alto (controllore/view)

    @Test
    public void inserimenti_ripetuti(){
        Grattacielo g = new Grattacielo(5,5);
        for(int valore=-10000; valore<=10000; valore++){
            g.inserisci(valore, new Posizione(0,0));
        }
        assertEquals(10000,g.getCella(0));
    }
    //i nuovi valori vengono inseriti senza problemi nelle celle già con valore

    @Test(expected = IllegalArgumentException.class)
    public void rimozioni_ripetute(){
        Grattacielo g = new Grattacielo(5,5);
        g.inserisci(40, new Posizione(0,0));
        g.rimuovi(new Posizione(0,0));
        g.rimuovi(new Posizione(0,0));
    }

    @Test(timeout = 30000)
    public void crescita_tempi_di_generazione(){
        Grattacielo g = new Grattacielo(6,20);
    }
    //quando la scacchiera di gioco diventa "troppo grande", il modulo alla base della mia applicazione non riesce a
    // terminare la generazione di una nuova partita in un tempo accettabile (non bastano 30 secondi per la creazione)
}