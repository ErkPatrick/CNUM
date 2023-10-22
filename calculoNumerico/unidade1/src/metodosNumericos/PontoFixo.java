package metodosNumericos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PontoFixo {
	static double a = 0.5;
	static double b = 1;
	static double x, y;
	static int iteracao = 0;
	static double precisao = 0.01;
	static boolean positivoA, positivoB, positivoY;
	static DecimalFormat deci = new DecimalFormat("0.0000");
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		positivoA = f(a) > 0;
		positivoB = f(b) > 0;
		if (positivoA == positivoB) {
			System.out.println("Não há raiz entre os intervalos");
			System.exit(0);
		}
		System.out.println("____________________________________");
		System.out.println("| ITER. |      X     |      f(x)   |");
		System.out.println("------------------------------------");
		do {
			if(iteracao == 0)
				x = (a + b)/2; // x0
			else
				x = p(x); // método do ponto fixo
			y = f(x);
			positivoY = y > 0;
			System.out.println("|   " + iteracao + "   |" + "   " + deci.format(x) + "   |" + "   " + deci.format(f(x)) + "   |");
			iteracao++;
		} while (Math.abs(y) > precisao);
		System.out.println("====================================");
		System.out.println("Para uma precisão de " + precisao + " temos:");
		System.out.println("Raiz = " + deci.format(x) + "\nf(x) = " + deci.format(y));
	}

	public static double f(double x) {
		double resultado = Math.pow(x, 3) - 9*x +5;
		return resultado;
	}
	public static double p(double x) {
		double resultado = (Math.pow(x, 3) + 5)/9;
		return resultado;
	}
}
