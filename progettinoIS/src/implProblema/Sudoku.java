package implProblema;

import backtrack.*;

public class Sudoku extends Problema<Posizione, Integer> {

	@Override
	protected Posizione primoPuntoDiScelta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Posizione prossimoPuntoDiScelta(Posizione ps, Integer s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Posizione ultimoPuntoDiScelta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer primaScelta(Posizione ps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer prossimaScelta(Integer s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer ultimaScelta(Posizione ps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean assegnabile(Integer scelta, Posizione puntoDiScelta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void assegna(Integer scelta, Posizione puntoDiScelta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deassegna(Integer scelta, Posizione puntoDiScelta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Posizione precedentePuntoDiScelta(Posizione puntoDiScelta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Integer ultimaSceltaAssegnata(Posizione puntoDiScelta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void scriviSoluzione(int nrsol) {
		// TODO Auto-generated method stub
		
	}

}
