package project_dm2018;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadyProject {

	static Scanner in = new Scanner(System.in);
	
	static ArrayList<Integer> Fill_arr_from_string(String str){
		ArrayList<Integer> out_arr = new ArrayList<>();
		
		System.out.println();
		
		for (int i = 0; i < str.length(); i++) {
			out_arr.add(Integer.parseInt(str.substring(i,i+1)));
		}
		
		return out_arr;
	}
	
	public static void main(String[] args) {
		//Natural a = new Natural(Fill_arr_from_string());
		//Natural b = new Natural(Fill_arr_from_string());
		
		Simple_gui app = new Simple_gui();
		app.setVisible(true);
		
		//System.out.println(Natural.NZER_N_B(a));
		
	}

}
