/**
 * 
 */
/**
 * @author Koval
 *
 */
package project_dm2018;
/*
package project_dm2018;

import java.util.ArrayList;

class Natural {
	int n_position;
	ArrayList<Integer> x = new ArrayList<Integer>();

	public  Natural(int n_position, ArrayList<Integer> a) {
		this.n_position = n_position;
		for (int i = 0; i < a.size(); i++) {
			x.add(a.get(i));
		}
	}
	public Natural() {
		n_position = 0;
		x = new ArrayList<Integer>();
	}
	
	static public void Print_natural(Natural a) {
		String str_a = a.x.toString();
		System.out.println(str_a);
	}
	
	public static void Swap_natural(Natural a, Natural b) {
		Natural c = a;
		a = b;
		b = c;
	}
	
	static int COM_MN_D(Natural a, Natural b) {
		int i =0;
		
		if(a.x.size() > b.x.size()) return 2;
		if(a.x.size() < b.x.size()) return 1;
		
		while(i < a.x.size()){
			if ((int)a.x.get(i) > (int)b.x.get(i)) {
				return 2;
			}else if ((int)a.x.get(i) < (int)b.x.get(i)) {
				return 1;
			}
			i++;
		}
		return 0;
	}
	
	static int NZER_N_B(Natural a) {
		if (a.x.get(0) == 0) {
			return 0;
		}
		return 1;
	}
	
	static void ADD_1N_N(Natural a){
		int i = 0;
		int new_v;
		int bufer = 0;
		
		while ((bufer == 1 || i == 0) && (a.x.size()-i-1>=0)) {
			new_v = a.x.get(a.x.size()-i-1);
			
			if (bufer == 1 || i==0) {
				new_v++;
				bufer = 0;
			}
			
			if (new_v > 9 && a.x.size()-i-1 == 0) {
				bufer = 1;
				new_v -= 10;
				a.x.set(0, new_v);
				a.x.add(0,1);
			}else if(new_v > 9 && a.x.size()-i-1 != 0) {
				bufer = 1;
				new_v -= 10;
				a.x.set(a.x.size()-i-1, new_v);
			}else {
				a.x.set(a.x.size()-i-1, new_v);
			}
			
			i++;
		}
	}
	
	static Natural ADD_NN_N(Natural a, Natural b) {
		
		int i = a.x.size()-1, j = b.x.size()-1;
		int test_v,bufer = 0;
		
		ArrayList<Integer> new_natural = new ArrayList<>();
		
		while (i >= 0 && j >= 0) {
			test_v = a.x.get(i)+b.x.get(j);
			
			if (bufer == 1) {
				test_v++;
				bufer = 0;
			}
			
			if(test_v > 9) {
				bufer = 1;
				test_v -= 10;
			}
			
			new_natural.add(0,test_v);
			if (i == 0 && j ==0 && bufer == 1) {
				new_natural.add(0,1);
				bufer = 0;
			} 
			i--;
			j--;
		}
		
		while (i>=0) {
			test_v = a.x.get(i) + bufer;
			
			if (test_v > 9 && i != 0) {
				test_v -= 10;
				bufer = 1;
				new_natural.add(0,test_v);
			}else if (i == 0 && test_v > 9) {
				new_natural.add(0,test_v-10);
				new_natural.add(0,1);
				bufer = 0;
			}else {
				new_natural.add(0,test_v);
				bufer = 0;
			}
			i--;
		}
		
		while (j>=0) {
			test_v = b.x.get(j) + bufer;
			
			if (test_v > 9 && j != 0) {
				test_v -= 10;
				bufer = 1;
				new_natural.add(0,test_v);
			}else if (j == 0 && test_v > 9) {
				new_natural.add(0,test_v-10);
				new_natural.add(0,1);
				bufer = 0;
			}else {
				new_natural.add(0,test_v);
				bufer = 0;
			}
			j--;
		}
		
		Natural c = new Natural(0,new_natural);
		
		return c;
	}
	
	static Natural SUB_NN_N(Natural a, Natural b) {
		Natural c = new Natural();
		int i = a.x.size()-1, j = b.x.size()-1;
		int test_v, bufer = 0;
		ArrayList<Integer> new_natural = new ArrayList<>();
		
		if (COM_MN_D(a,b) == 1) {
			Swap_natural(a,b);
		}
		
		while (i >= 0 && j >= 0) {
			test_v = a.x.get(i) - b.x.get(j) - bufer;
			bufer=0;
			
			if (test_v < 0) {
				bufer = 1;
				test_v += 10;
			}
		
			new_natural.add(0,test_v);
			i--;
			j--;
		}
		
		while(i >= 0) {
			test_v = a.x.get(i) - bufer;
			bufer = 0;
			
			if (test_v < 0) {
				bufer = 1;
				test_v += 10;
			}
			new_natural.add(0,test_v);
			i--;
		}
		
		if(new_natural.get(0) < 0) {
			return null;
		}
		
		c.x = new_natural;
		return c;
	}
	
	static void MUL_ND_N(Natural a, int mul) {
		int i = a.x.size()-1;
		int test_v,bufer = 0;
		
		while (i >= 0) {
			test_v = a.x.get(i)*mul + bufer;
			
			bufer = test_v / 10;
			test_v %= 10;
			
			if (bufer > 0 && i == 0) {
				a.x.set(i, test_v);
				
				while (bufer != 0) {
					a.x.add(0,bufer%10);
					
					bufer /= 10;
				}
			}else {
				a.x.set(i, test_v);
			}
			
			i--;
		}
	}
	
	static Natural MUL_NK_N(Natural a, int k) {
		Natural a1 = new Natural(0,new ArrayList<Integer>(a.x));
		for(int i = 0; i<k; i++) {
			a1.x.add(0);
		}
		return a1;
	}
	
	static Natural MUL_NN_N(Natural a, Natural b) {
		Natural c = new Natural();
		
		if (COM_MN_D(a,b) == 1) {
			Swap_natural(a,b);
		}
		
		int i = 0, j = b.x.size() - 1;
		Natural g = new Natural();
		int mul;
		
		
		while(j >= 0) {
			mul = b.x.get(j);
			g.x = new ArrayList<Integer>(a.x);
			MUL_ND_N(g,mul);
			g=MUL_NK_N(g,i);
			c = ADD_NN_N(c,g);
			j--;
			i++;
		}
		
		return c;
	}
	
	static Natural SUB_NDN_N(Natural a, Natural b, int mul) {
		Natural c = new Natural();
		c.x = new ArrayList<Integer>(b.x);
		MUL_ND_N(c,mul);
		Natural value = SUB_NN_N(a,c);
		while (value.x.get(0) == 0) {
			value.x.remove(0);
		}
		return value;
	}
	
	static int DIV_NN_DK(Natural a, Natural b) {
		int div = 1;
		Natural b1 = new Natural(0, new ArrayList<Integer>(b.x));
		Natural a1 = new Natural(0, new ArrayList<Integer>(a.x));
		
		if (COM_MN_D(a,b) == 1) {
			Swap_natural(a1,b1);
		}
		int i = a1.x.size() - 1, j = b1.x.size() - 1;
		
		if (a1.x.get(0) < b1.x.get(0)) {
			b1=MUL_NK_N(b1,i-j-1);
		}else {
			b1=MUL_NK_N(b1,i-j);
		}
		
		while (COM_MN_D(b1,SUB_NDN_N(a1,b1,div)) == 1) {
			div++;
		}
		
		return div;
	}
	
	static Natural DIV_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(0,new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(0,new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		int nahyi;
		
		if (COM_MN_D(a1,b1) == 1) {
			Swap_natural(a1,b1);
		}
		while (COM_MN_D(b1,a1) == 1) {
			c1.x.add(DIV_NN_DK(a1,b1));
			
			if (a1.x.get(0) < b1.x.get(0)) {
				nahyi=a1.x.size()-b1.x.size()-1;
			}else {
				nahyi=a1.x.size()-b1.x.size();
			}
			
			a1 = SUB_NDN_N(a1,MUL_NK_N(b1,nahyi),DIV_NN_DK(a1,b1));
			Print_natural(b1);
			Print_natural(a1);
			System.out.println();
			//c1.x.add(0,DIV_NN_DK(a1,b1));
		}
		
		return c1;
	}
	
}
*/