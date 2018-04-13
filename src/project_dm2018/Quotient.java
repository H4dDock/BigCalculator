package project_dm2018;

import java.util.ArrayList;

public class Quotient {
	Zahlen nomerator;
	Natural denomerator;
	
	public Quotient(Zahlen a, Natural b){
		nomerator = a;
		denomerator = b;
	}
	
	public Quotient() {
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Integer>x1 = new ArrayList<>();
		x.add(0);
		nomerator = new Zahlen(2,x);
		x1.add(1);
		denomerator = new Natural(x1);
	}
	 
	static void Print_Quotient(Quotient q) {
		Zahlen.Print_Zahlen(q.nomerator);
		System.out.printf("-----------\n	");
		Natural.Print_natural(q.denomerator);
	}
	
	public static Quotient StringToQuotient(String a) {
		int n = (a.charAt(0) == '+') ? 2 : 1;
		ArrayList<Integer> x1 = new ArrayList<>();
		ArrayList<Integer> x2 = new ArrayList<>();
		
		int i;
		for ( i = 1; a.charAt(i) != '/'; i++) {
			x1.add(a.charAt(i) - '0');
		}
		
		Zahlen zah = new Zahlen(n,x1);
		i++;
		
		for(;i < a.length(); i++ ) {
			x2.add(a.charAt(i) - '0');
		}
		Natural nat = new Natural(x2);
		
		Quotient q = new Quotient(zah,nat);
		
		return q;
	}
	
	public static String QuotientToString(Quotient q) {
		String s = "";
		
		s += Zahlen.ZahlenToString(q.nomerator);
		s += "/";
		s += Natural.NaturalToNormalString(q.denomerator);
		
		return s;
	}
	
	static Quotient RED_Q_Q(Quotient q) {
		Quotient q1 = new Quotient(q.nomerator,q.denomerator);
		Zahlen nom = new Zahlen(q1.nomerator.n,q1.nomerator.number);
		
		q1.nomerator = Zahlen.TRANS_N_Z(Natural.DIV_NN_N(Zahlen.TRANS_Z_N_NoMath(q1.nomerator),Natural.GCF_NN_N(Zahlen.TRANS_Z_N_NoMath(q1.nomerator),q1.denomerator)));
		q1.nomerator.n = nom.n;
		q1.denomerator = Natural.DIV_NN_N(q1.denomerator,Natural.GCF_NN_N(Zahlen.TRANS_Z_N_NoMath(nom),q1.denomerator));
		
		return q1;
	}
	
	static int INT_Q_N(Quotient q) {
		Quotient q1 = new Quotient(q.nomerator,q.denomerator);
		
		if(Zahlen.MOD_ZZ_Z(q1.nomerator, Zahlen.TRANS_N_Z(q1.denomerator)).number.get(0) == 0) {
			return 1;
		}
		
		return 0;
	}
	
	static Quotient TRANS_Z_Q(Zahlen a) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Natural b1 = new Natural();
		b1.x.add(1);
		Quotient q1 = new Quotient(a1,b1);
		
		return q1;
	}
	
	static Zahlen TRANS_Q_Z(Quotient q) {
		Quotient q1 = new Quotient(q.nomerator,q.denomerator);
		Zahlen c = null;
		
		if(q1.denomerator.x.size() == 1 && q1.denomerator.x.get(0) == 1 ) {
			c = new Zahlen(q1.nomerator.n,q1.nomerator.number);
		}
		
		return c;
	}
	
	static Quotient ADD_QQ_Q(Quotient a, Quotient b) {
		Quotient a1 = new Quotient(a.nomerator,a.denomerator);
		Quotient b1 = new Quotient(b.nomerator,b.denomerator);
		Quotient c = new Quotient();
		
		if (a1.denomerator != b1.denomerator) {
			Natural gcf = Natural.LCM_NN_N(a1.denomerator, b1.denomerator);
			a1.nomerator = Zahlen.MUL_ZZ_Z(a1.nomerator, Zahlen.TRANS_N_Z(Natural.DIV_NN_N(gcf, a1.denomerator)));
			b1.nomerator = Zahlen.MUL_ZZ_Z(b1.nomerator, Zahlen.TRANS_N_Z(Natural.DIV_NN_N(gcf, b1.denomerator)));
			a1.denomerator = gcf;
			b1.denomerator = gcf;
		}
		
		c.nomerator = Zahlen.ADD_ZZ_Z(a1.nomerator, b1.nomerator);
		c.denomerator = a1.denomerator;
		
		if(Zahlen.POZ_Z_D(c.nomerator) != 0){
			c = RED_Q_Q(c);
		}
		
		return c;
	}
	
	static Quotient SUB_QQ_Q(Quotient a, Quotient b) {
		Quotient a1 = new Quotient(a.nomerator,a.denomerator);
		Quotient b1 = new Quotient(b.nomerator,b.denomerator);
		Quotient c = new Quotient();
		
		if (a1.denomerator != b1.denomerator) {
			Natural gcf = Natural.LCM_NN_N(a1.denomerator, b1.denomerator);
			a1.nomerator = Zahlen.MUL_ZZ_Z(a1.nomerator, Zahlen.TRANS_N_Z(Natural.DIV_NN_N(gcf, a1.denomerator)));
			b1.nomerator = Zahlen.MUL_ZZ_Z(b1.nomerator, Zahlen.TRANS_N_Z(Natural.DIV_NN_N(gcf, b1.denomerator)));
			a1.denomerator = gcf;
			b1.denomerator = gcf;
		}
		
		c.nomerator = Zahlen.SUB_ZZ_Z(a1.nomerator, b1.nomerator);
		c.denomerator = a1.denomerator;
		
		if(Zahlen.POZ_Z_D(c.nomerator) != 0){
			c = RED_Q_Q(c);
		}
		
		return c;
	}
	
	static Quotient MUL_QQ_Q(Quotient a, Quotient b) {
		Quotient a1 = new Quotient(a.nomerator,a.denomerator);
		Quotient b1 = new Quotient(b.nomerator,b.denomerator);
		Quotient c = new Quotient();
		
		c.nomerator = Zahlen.MUL_ZZ_Z(a1.nomerator, b1.nomerator);
		c.denomerator = Natural.MUL_NN_N(a1.denomerator, b1.denomerator);
		
		if(Zahlen.POZ_Z_D(c.nomerator) != 0){
			c = RED_Q_Q(c);
		}
		
		return c;
	}
	
	static Quotient DIV_QQ_Q(Quotient a, Quotient b) {
		Quotient a1 = new Quotient(a.nomerator,a.denomerator);
		Quotient b1 = new Quotient(b.nomerator,b.denomerator);
		Quotient c = new Quotient();
	
		if(Zahlen.POZ_Z_D(b1.nomerator) == 0) {
			return null;
		}
		
		c.nomerator = Zahlen.MUL_ZZ_Z(a1.nomerator,Zahlen.TRANS_N_Z(b1.denomerator));
		c.denomerator = Natural.MUL_NN_N(a1.denomerator, Zahlen.TRANS_Z_N_NoMath(b1.nomerator));
		
		if(a.nomerator.n + b.nomerator.n == 4 || a.nomerator.n + b.nomerator.n == 2 && a.nomerator.n != 0 && b.nomerator.n != 0) {
			c.nomerator.n = 2;
		}else if (a.nomerator.n + b.nomerator.n == 3) {
			c.nomerator.n = 1;
		}else {
			c.nomerator.n = 0;
		}
		
		if(Zahlen.POZ_Z_D(c.nomerator) != 0){
			c = RED_Q_Q(c);
		}
		
		return c;
	}
}