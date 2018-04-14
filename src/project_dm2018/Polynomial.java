package project_dm2018;

import java.util.ArrayList;
import java.util.Scanner;

/*
 Коваль Максим гр 7307
 */

public class Polynomial {
	ArrayList<Quotient> coefficient;
	static Scanner in = new Scanner(System.in);
	
	public Polynomial() {
		coefficient = new ArrayList<>();
	}
	
	public Polynomial(ArrayList<Quotient> input_list) {
		coefficient = input_list;
	}
	
	public static Polynomial StringToPolynomial(String s) {
		ArrayList<Quotient> coef = new ArrayList<>();
		int i = 0;
		int n;
		int deg = -1;
		
		while (i < s.length()) {
			n = (s.charAt(i) == '+') ? 2 : 1;
			i++;
			
			ArrayList<Integer> xnat = new ArrayList<>();
			ArrayList<Integer> xzah = new ArrayList<>();
			
			while(s.charAt(i) != '/') {
				xzah.add(s.charAt(i)-'0');
				i++;
			}
			i++;
			while(i < s.length()) {
				if(s.charAt(i) == 'x') break;
				xnat.add(s.charAt(i)-'0');
				i++;
			}
			
			if(i+1 < s.length()) {
				i++;
				if (s.charAt(i) != '+' && s.charAt(i) != '-') {
					i++;
					int new_deg;
					String sdeg = "";
					while (i < s.length()) {
						if(s.charAt(i) == '+' || s.charAt(i) == '-') break;
						sdeg += s.charAt(i);
						i++;
					}
					
					new_deg = Integer.parseInt(sdeg);
					if (deg == -1) {
						for(int j = 0; j <= new_deg; j++) {
							coef.add(new Quotient());
						}
					}
					deg = new_deg;
				}else {
					int new_deg = 1;
					if (deg == -1) {
						for(int j = 0; j <= new_deg; j++) {
							coef.add(new Quotient());
						}
					}
					deg = new_deg;
				}
			}else if(i == s.length()) {
				int new_deg = 0;
				if (deg == -1) {
					for(int j = 0; j <= new_deg; j++) {
						coef.add(new Quotient());
					}
				}
				deg = new_deg;
			}
			else if(s.charAt(i) == 'x' && i+1 == s.length()) {
				int new_deg = 1;
				if (deg == -1) {
					for(int j = 0; j <= new_deg; j++) {
						coef.add(new Quotient());
					}
				}
				deg = new_deg;	
				i++;
			}
			
			Natural nat = new Natural(xnat);
			Zahlen zah = new Zahlen(n,xzah);
			coef.set(coef.size() - deg - 1, new Quotient(zah,nat));
			
		}
		
		Polynomial p = new Polynomial(coef);
		return p;
	}
	
	public static String PolynomialToString(Polynomial p) {
		String s = "";
		int j = p.coefficient.size()-1;
		
		for (int i = 0; i < p.coefficient.size(); i++) {
			if(p.coefficient.get(i).nomerator.number.get(0) != 0) {
				s += Quotient.QuotientToString(p.coefficient.get(i));
				s += "x^" + j;
			}
			j--;
		}
		
		return s;
	}
	
	private static ArrayList<Integer> Fill_arr_from_string() {
		ArrayList<Integer> out_arr = new ArrayList<>();
		String str_to_arr = in.nextLine();
		
		System.out.println();
		
		for (int i = 0; i < str_to_arr.length(); i++) {
			out_arr.add(Integer.parseInt(str_to_arr.substring(i,i+1)));
		}
		
		return out_arr;
	}
	
	static void FillPolynomial(Polynomial a, int n) {
		Quotient q1 = new Quotient();
		for(int i = 0; i < n; i++){
			System.out.println("Enter Z");
			int k = in.nextInt();
			in.nextLine();
			Zahlen z1 = new Zahlen(k,Fill_arr_from_string());
			System.out.println("Enter N");
			Natural b = new Natural(Fill_arr_from_string());
			q1 = new Quotient(z1,b);
			a.coefficient.add(q1);
		}
	}
	
	public static Polynomial AddSomeNull (Polynomial p, int n) {
		ArrayList<Integer> help = new ArrayList<>();
		help.add(0);
		ArrayList<Integer> kostyl = new ArrayList<>();
		kostyl.add(1);
		Quotient q = new Quotient(new Zahlen(0,help), new Natural(kostyl));
		
		for(int i = 0; i < n; i++) {
			p.coefficient.add(q);
		}
		
		return p;
		
	}
	
	static void PrintPolynomial(Polynomial a) {
		int n = a.coefficient.size();
		for (int i = 0; i < n; i++) {
			Quotient.Print_Quotient(a.coefficient.get(i));
		}
	}
	
	static Polynomial ADD_PP_P(Polynomial a,Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial c = new Polynomial();
	
		int i = a1.coefficient.size()-1, j = b1.coefficient.size()-1;
		
		while(i>=0 && j>=0) {
			c.coefficient.add(0,Quotient.ADD_QQ_Q(a1.coefficient.get(i),b1.coefficient.get(j)));
			i--;
			j--;
		}
		while(i >= 0) {
			c.coefficient.add(0,a1.coefficient.get(i));
			i--;
		}
		while(j >= 0) {
			c.coefficient.add(0,b1.coefficient.get(j));
			j--;
		}
		
		return c;
	}
	
	static Polynomial SUB_PP_P(Polynomial a,Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial c = new Polynomial();

		int i = a1.coefficient.size()-1, j = b1.coefficient.size()-1;
		
		while(i>=0 && j>=0) {
			c.coefficient.add(0,Quotient.SUB_QQ_Q(a1.coefficient.get(i),b1.coefficient.get(j)));
			i--;
			j--;
		}
		while(i >= 0) {
			c.coefficient.add(0,a1.coefficient.get(i));
			i--;
		}
		while(j >= 0) {
			c.coefficient.add(0,b1.coefficient.get(j));
			j--;
		}
		
		return c;
	}
	
	static Polynomial MUL_PQ_P(Polynomial a, Quotient b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Quotient b1 = new Quotient(b.nomerator, b.denomerator);
		Polynomial c = new Polynomial();
		
		int i = a1.coefficient.size()-1;
		
		while(i >= 0) {
			c.coefficient.add(0, Quotient.MUL_QQ_Q(a1.coefficient.get(i), b1));
			i--;
		}
		
		return c;
	}
	
	static Polynomial MUL_PXK_P(Polynomial a, int k) {
		Polynomial a1 = new Polynomial(a.coefficient);
		ArrayList<Integer> n1 = new ArrayList<>();
		n1.add(1);
		ArrayList<Integer> z1 = new ArrayList<>();
		z1.add(1);
		for(int j = 0; j < k; j++) {
			z1.add(0);
		}
		Quotient q = new Quotient(new Zahlen(2,z1), new Natural(n1));
		Polynomial c = MUL_PQ_P(a1,q);
		
		return c;
	}
	
	static Quotient LED_P_Q(Polynomial p) {
		Polynomial p1 = new Polynomial(p.coefficient);
		Quotient q = new Quotient(p1.coefficient.get(0).nomerator,p1.coefficient.get(0).denomerator);
		return q;
	}
	
	static int DEG_P_N(Polynomial p) {
		return p.coefficient.size()-1;
	}
	
	static Quotient FAC_P_Q(Polynomial p) {
		Polynomial p1 = new Polynomial(p.coefficient);
		Natural nok = Natural.LCM_NN_N(Zahlen.TRANS_Z_N_NoMath(p1.coefficient.get(0).nomerator), Zahlen.TRANS_Z_N_NoMath(p1.coefficient.get(1).nomerator));
		Natural nod = Natural.GCF_NN_N(p1.coefficient.get(0).denomerator, p1.coefficient.get(1).denomerator);
		int n = p1.coefficient.size();
		
		for (int i = 2; i < n; i++) {
			nok = Natural.LCM_NN_N(nok, Zahlen.TRANS_Z_N_NoMath(p1.coefficient.get(i).nomerator));
			nod = Natural.GCF_NN_N(nod, p1.coefficient.get(i).denomerator);
		}
		return new Quotient(Zahlen.TRANS_N_Z(nok),nod);
	}
	
	static Polynomial MUL_PP_P(Polynomial a, Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial c = new Polynomial();
		int n = b1.coefficient.size()-1;
		
		for(; n >= 0; n--) {
			c = ADD_PP_P(c,AddSomeNull(MUL_PQ_P(a1,b1.coefficient.get(n)),b1.coefficient.size() - 1 - n ));
		}
		
		return c;
	}
	
	static Polynomial DIV_PP_P(Polynomial a, Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial c = new Polynomial();
		Natural help = new Natural();
		help.x.add(1);
		int i = 0;
		
		while (a1.coefficient.size() >= b1.coefficient.size()) {
			c.coefficient.add(Quotient.DIV_QQ_Q(a1.coefficient.get(0),b1.coefficient.get(0)));
			a1 = SUB_PP_P(a1,AddSomeNull(MUL_PQ_P(b1,c.coefficient.get(i)),a1.coefficient.size()-b1.coefficient.size()));
			while(a1.coefficient.size() != 0) {
				if(a1.coefficient.get(0).nomerator.n == 0) {
					a1.coefficient.remove(0);
				}else {
					break;
				}
			}
			i++;
		}
	
		return c;
	}
	
	static Polynomial MOD_PP_P(Polynomial a, Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial c = new Polynomial();
		Natural help = new Natural();
		help.x.add(1);
		int i = 0;
		
		while (a1.coefficient.size() >= b1.coefficient.size()) {
			c.coefficient.add(Quotient.DIV_QQ_Q(a1.coefficient.get(0),b1.coefficient.get(0)));
			a1 = SUB_PP_P(a1,AddSomeNull(MUL_PQ_P(b1,c.coefficient.get(i)),a1.coefficient.size()-b1.coefficient.size()));
			while(a1.coefficient.size() != 0) {
				if(a1.coefficient.get(0).nomerator.n == 0) {
					a1.coefficient.remove(0);
				}else {
					break;
				}
			}
			i++;
		}
	
		return a1;
	}
	
	static Polynomial GCF_PP_P(Polynomial a, Polynomial b) {
		Polynomial a1 = new Polynomial(a.coefficient);
		Polynomial b1 = new Polynomial(b.coefficient);
		Polynomial r;
		
		if(a1.coefficient.size() < b1.coefficient.size()) {
			r = new Polynomial(a1.coefficient);
			a1 = b1;
			b1 = r;
		}else if(a1.coefficient.size() == b1.coefficient.size() && Quotient.SUB_QQ_Q(a1.coefficient.get(0), b1.coefficient.get(0)).nomerator.n != 1) {
			r = new Polynomial(a1.coefficient);
			a1 = b1;
			b1 = r;
		}
		
		r = MOD_PP_P(a1,b1);
		while(r.coefficient.size() != 0){
			r = MOD_PP_P(a1,b1);
			a1 = b1;
			b1 = r;
		}
		
		return a1;
	}
	
	static Polynomial DER_P_P(Polynomial p) {
		Polynomial p1 = new Polynomial(p.coefficient);
		Natural a = new Natural();
		a.x.add(1);
		Zahlen b = new Zahlen();
		b.number.add(0);
		b.n = 2;
		Quotient q = new Quotient(b,a);
		
		
		int n = p1.coefficient.size()-1;
		
		for(;n>=0;n--) {
			q.nomerator.number.set(0, n);
			p1.coefficient.set(p1.coefficient.size()-1 - n,Quotient.MUL_QQ_Q(p1.coefficient.get(p1.coefficient.size()-1 - n), q));
		}
		p1.coefficient.remove(p1.coefficient.size()-1);
		
		return p1;
	}
	
	static Polynomial NMR_P_P(Polynomial p) {
		Polynomial p1 = new Polynomial(p.coefficient);
		
		Polynomial output = DIV_PP_P(p1,MUL_PP_P(DER_P_P(p1),GCF_PP_P(DER_P_P(p1),p1)));
		
		return output;
	}
}
