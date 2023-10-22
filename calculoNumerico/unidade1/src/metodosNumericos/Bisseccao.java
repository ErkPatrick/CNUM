package metodosNumericos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Bisseccao {
	static double a = 0.5;
	static double b = 1;
	static double x, y;
	static double precisao = 0.01;
	static boolean positivoA, positivoB, positivoY;
	static DecimalFormat deci = new DecimalFormat("0.0000");
	static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		positivoA = f(a) > 0;
		positivoB = f(b) > 0;
		if(positivoA == positivoB) {
			System.out.println("Não há raiz entre os intervalos");
			System.exit(0);
		}
		System.out.println("______________________________________________________");
		System.out.println("|      a     |      b     |      x     |     f(x)    |");
		System.out.println("------------------------------------------------------");
		do{
			x = (a + b)/2; // bissecção
			y = f(x);
			positivoY = y > 0;
			System.out.println("|   " + deci.format(a) + "   |" + "   " + deci.format(b) + "   |" + "   " + deci.format(x) + "   |" + "   " + deci.format(y) + "   |");
			if(positivoA == positivoY)
				a = x;
			else
				b = x;
		}while(Math.abs(y) > precisao);
		System.out.println("======================================================");
		System.out.println("Para uma precisão de " + precisao + " temos:");
		System.out.println("Raiz = " + deci.format(x) + "\nf(x) = " + deci.format(y));
	}
	public static double f(double x) {
		double resultado = Math.pow(x, 3) - 9*x + 5;
		return resultado;
	}
}
