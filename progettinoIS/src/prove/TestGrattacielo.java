package prove;

import implProblema.Grattacielo;

public class TestGrattacielo {
	public static void main(String[] args) {
		int []n= {2,1,3,2,5};
		int []s= {2,2,2,4,1};
		int []e= {3,2,3,2,1};
		int []o= {2,3,3,1,3};
		Grattacielo g=new Grattacielo(5,n,s,e,o);
		
		
		Grattacielo g1=new Grattacielo(6,1);
		g1.risolvi();
		
	}
}
