package project_dm2018;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Simple_gui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Press");
	private JButton buttonh = new JButton("Help");
	private JTextField input1 = new JTextField("",5);
	private JTextField input2 = new JTextField("",5);
	private JTextField input3 = new JTextField("",5);
	private JLabel label1 = new JLabel("Input1:");
	private JLabel label2 = new JLabel("Input2:");
	private JLabel label3 = new JLabel("Input3(optional):");
	
	
	
	String Field[] = {
			"Natural (N)",
			"Zahlen (Z)",
			"Quotient (Q)",
			"Polynomial (P)"
		};
	JComboBox<String> comboboxField = new JComboBox<>(Field);
	JComboBox<String> comboboxFunc = new JComboBox<>();
	
	public Simple_gui() {
		super("DM 2018");
		this.setBounds(100,100,450,180);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(5,4));
		container.add(label1);
		container.add(input1);
		container.add(label2);
		container.add(input2);
		container.add(label3);
		container.add(input3);
		
		comboboxField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choose = (String)comboboxField.getSelectedItem();
				switch (choose) {
				case "Natural (N)":
					String function[] = {
							"COM_MN_D",
							"NZER_N_B",
							"ADD_1N_N",
							"ADD_NN_N",
							"ADD_NN_N",
							"SUB_NN_N",
							"MUL_ND_N",
							"MUL_NK_N",
							"MUL_NN_N",
							"SUB_NDN_N",
							"DIV_NN_DK",
							"DIV_NN_N",
							"GCF_NN_N",
							"LCM_NN_N"
						};
					container.remove(button);
					container.remove(comboboxFunc);
					comboboxFunc = new JComboBox<>(function);
					container.add(comboboxFunc);
					container.add(button);
					container.add(buttonh);
					container.revalidate();
					break;
				case "Zahlen (Z)":
					String function1[] = {
							"ABS_Z_N",
							"POZ_Z_D",
							"MUL_ZM_Z",
							"TRANS_N_Z",
							"TRANS_Z_N",
							"ADD_ZZ_Z",
							"SUB_ZZ_Z",
							"MUL_ZZ_Z",
							"DIV_ZZ_Z",
							"MOD_ZZ_Z"
						};
					container.remove(button);
					container.remove(comboboxFunc);
					comboboxFunc = new JComboBox<>(function1);
					container.add(comboboxFunc);
					container.add(button);
					container.add(buttonh);
					container.revalidate();
					break;
				case "Quotient (Q)":
					String function2[] = {
							"RED_QQ_Q",
							"INT_Q_B",
							"TRANS_Z_Q",
							"TRANS_Q_Z",
							"ADD_QQ_Q",
							"SUB_QQ_Q",
							"MUL_QQ_Q",
							"DIV_QQ_Q"
						};
					container.remove(button);
					container.remove(comboboxFunc);
					comboboxFunc = new JComboBox<>(function2);
					container.add(comboboxFunc);
					container.add(button);
					container.add(buttonh);
					container.revalidate();
					break;
				case "Polynomial (P)":
					String function3[] = {
							"ADD_PP_P",
							"SUB_PP_P",
							"MUL_PQ_P",
							"MUL_PXK_P",
							"LED_P_Q",
							"DEG_P_N",
							"FAC_P_Q",
							"MUL_PP_P",
							"DIV_PP_P",
							"MOD_PP_P",
							"GCF_PP_P",
							"DER_P_P",
							"NMR_P_P"
						};
					container.remove(button);
					container.remove(comboboxFunc);
					comboboxFunc = new JComboBox<>(function3);
					container.add(comboboxFunc);
					container.add(button);
					container.add(buttonh);
					container.revalidate();
					break;
				}
			}
		});
		container.add(comboboxField);
		container.add(comboboxFunc);
		
		button.addActionListener(new ButtonEventListener());
		container.add(button);
		buttonh.addActionListener(new HelpEventListener());
		container.add(buttonh);
	}
	
	class HelpEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String help = "";
			help+="Для того, чтобы выполнить какое-либо действие, достаточно ввести необходимое для работы функции количество аргументов и нажать кнопку Press\n";
			help+="Аргументы вводить поочередено, то есть аргумент 1 в поле input1 и т.д. \n";
			help+="Интерфейс не поддерживает проверку к ввода, в названии каждой функции есть необходимая информация по вводу\n";
			help+="Для ввода целых, рациональных чисел (а следовательно и при вводе члена многочлена) необходимо перед числом дописать его знак\n";
			help+="Для ввода рациональных чисел числитель от знаменателя следует отделить при помощи знака / \n";
			help+="Каждый член многочлена вводится как отдельное рациональное число, вводить многочлен следует в виде: \n";
			help+="(Дробь старшего члена)x^(степень старшего члена)+...+(Дробь младшего члена)x^(степень старшего члена если имеется)\n";
			help+="Пример: +5/1x^6+4/1x^3+4/1 (Ввод вооспримчив к пробелам)";
			JOptionPane.showMessageDialog(null, help,"output",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	class ButtonEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String choose = (String)comboboxFunc.getSelectedItem();
			Natural a,b,c;
			Zahlen a1,b1,c1;
			Quotient a2,b2,c2;
			Polynomial a3,b3,c3;
			int d;
			String message;
			switch (choose) {
			case "COM_MN_D":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				message = "" + Natural.COM_MN_D(a,b);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "NZER_N_B":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				message = "" + Natural.NZER_N_B(a);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "ADD_1N_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				c = Natural.ADD_1N_N(a);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "ADD_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.ADD_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "SUB_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.SUB_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_ND_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				d = Integer.parseInt(input2.getText());
				c = Natural.MUL_ND_N(a,d);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_NK_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				d = Integer.parseInt(input2.getText());
				c = Natural.MUL_NK_N(a,d);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.MUL_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "SUB_NDN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				d = Integer.parseInt(input3.getText());
				c = Natural.SUB_NDN_N(a,d,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DIV_NN_DK":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				d = Natural.DIV_NN_DK(a,b);
				message = "" + d;
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DIV_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.DIV_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MOD_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.MOD_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
			
			case "GCF_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.GCF_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "LCM_NN_N":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				b = new Natural(ReadyProject.Fill_arr_from_string(input2.getText()));
				c = Natural.LCM_NN_N(a,b);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "ABS_Z_N":
				a1 = Zahlen.StringToZahlen(input1.getText());
				message = "" + Natural.NaturalToNormalString(Zahlen.ABS_Z_N(a1));
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "POZ_Z_D":
				a1 = Zahlen.StringToZahlen(input1.getText());
				message = "" + Zahlen.POZ_Z_D(a1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
			
			case "MUL_ZM_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				c1 = Zahlen.MUL_ZM_Z(a1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "TRANS_N_Z":
				a = new Natural(ReadyProject.Fill_arr_from_string(input1.getText()));
				c1 = Zahlen.TRANS_N_Z(a);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "TRANS_Z_N":
				a1 = Zahlen.StringToZahlen(input1.getText());
				c = Zahlen.TRANS_Z_N(a1);
				message = "" + Natural.NaturalToNormalString(c);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "ADD_ZZ_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				b1 = Zahlen.StringToZahlen(input2.getText());
				c1 = Zahlen.ADD_ZZ_Z(a1,b1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "SUB_ZZ_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				b1 = Zahlen.StringToZahlen(input2.getText());
				c1 = Zahlen.SUB_ZZ_Z(a1,b1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_ZZ_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				b1 = Zahlen.StringToZahlen(input2.getText());
				c1 = Zahlen.MUL_ZZ_Z(a1,b1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DIV_ZZ_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				b1 = Zahlen.StringToZahlen(input2.getText());
				c1 = Zahlen.DIV_ZZ_Z(a1,b1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MOD_ZZ_Z":
				a1 = Zahlen.StringToZahlen(input1.getText());
				b1 = Zahlen.StringToZahlen(input2.getText());
				c1 = Zahlen.MOD_ZZ_Z(a1,b1);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "RED_Q_Q":
				a2 = Quotient.StringToQuotient(input1.getText());
				c2 = Quotient.RED_Q_Q(a2);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "INT_Q_B":
				a2 = Quotient.StringToQuotient(input1.getText());
				message = "" + Quotient.INT_Q_N(a2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "TRANS_Z_Q":
				a1 = Zahlen.StringToZahlen(input1.getText());
				c2 = Quotient.TRANS_Z_Q(a1);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "TRANS_Q_Z":
				a2 = Quotient.StringToQuotient(input1.getText());
				c1 = Quotient.TRANS_Q_Z(a2);
				message = "" + Zahlen.ZahlenToString(c1);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
			
			case "ADD_QQ_Q":
				a2 = Quotient.StringToQuotient(input1.getText());
				b2 = Quotient.StringToQuotient(input2.getText());
				c2 = Quotient.ADD_QQ_Q(a2,b2);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "SUB_QQ_Q":	
				a2 = Quotient.StringToQuotient(input1.getText());
				b2 = Quotient.StringToQuotient(input2.getText());
				c2 = Quotient.SUB_QQ_Q(a2,b2);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_QQ_Q":
				a2 = Quotient.StringToQuotient(input1.getText());
				b2 = Quotient.StringToQuotient(input2.getText());
				c2 = Quotient.MUL_QQ_Q(a2,b2);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DIV_QQ_Q":
				a2 = Quotient.StringToQuotient(input1.getText());
				b2 = Quotient.StringToQuotient(input2.getText());
				c2 = Quotient.DIV_QQ_Q(a2,b2);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "ADD_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				Polynomial.PrintPolynomial(a3);
				b3 = Polynomial.StringToPolynomial(input2.getText());
				Polynomial.PrintPolynomial(b3);
				c3 = Polynomial.ADD_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "SUB_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b3 = Polynomial.StringToPolynomial(input2.getText());
				c3 = Polynomial.SUB_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_PQ_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b2 = Quotient.StringToQuotient(input2.getText());
				c3 = Polynomial.MUL_PQ_P(a3, b2);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_PXK_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				d = Integer.parseInt(input2.getText());
				c3 = Polynomial.MUL_PXK_P(a3, d);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "LED_P_Q":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				c2 = Polynomial.LED_P_Q(a3);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DEG_P_N":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				d = Polynomial.DEG_P_N(a3);
				message = "" + d;
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "FAC_P_Q":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				c2 = Polynomial.FAC_P_Q(a3);
				message = "" + Quotient.QuotientToString(c2);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MUL_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b3 = Polynomial.StringToPolynomial(input2.getText());
				c3 = Polynomial.MUL_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DIV_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b3 = Polynomial.StringToPolynomial(input2.getText());
				c3 = Polynomial.DIV_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "MOD_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b3 = Polynomial.StringToPolynomial(input2.getText());
				c3 = Polynomial.MOD_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "GCF_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				b3 = Polynomial.StringToPolynomial(input2.getText());
				c3 = Polynomial.GCF_PP_P(a3, b3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "DER_P_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				c3 = Polynomial.DER_P_P(a3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "NMR_PP_P":
				a3 = Polynomial.StringToPolynomial(input1.getText());
				c3 = Polynomial.NMR_P_P(a3);
				message = "" + Polynomial.PolynomialToString(c3);
				JOptionPane.showMessageDialog(null, message,"output",JOptionPane.PLAIN_MESSAGE);
				break;
			}
		}
	}
}
