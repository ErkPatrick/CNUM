package metodosNumericos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FalsaPosicao {
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
		if (positivoA == positivoB) {
			System.out.println("Não há raiz entre os intervalos");
			System.exit(0);
		}
		System.out.println("_________________________________________________________________________________");
		System.out.println("|      a     |     f(a)    |      b     |    f(b)    |      x     |     f(x)    |");
		System.out.println("---------------------------------------------------------------------------------");
		do {
			x = (a * f(b) - b * f(a)) / (f(b) - f(a)); // método da Falsa Posição
			y = f(x);
			positivoY = y > 0;
			System.out.println("|   " + deci.format(a) + "   |" + "   " + deci.format(f(a)) + "   |" + "   "
					+ deci.format(b) + "   |" + "   " + deci.format(f(b)) + "   |" + "   " + deci.format(x) + "   |"
					+ "   " + deci.format(f(x)) + "   |");
			if (positivoA == positivoY)
				a = x;
			else
				b = x;
		} while (Math.abs(y) > precisao);
		System.out.println("=================================================================================");
		System.out.println("Para uma precisão de " + precisao + " temos:");
		System.out.println("Raiz = " + deci.format(x) + "\nf(x) = " + deci.format(y));
	}

	public static double f(double x) {
		double resultado = Math.pow(x, 3) - 9*x +5;
		return resultado;
	}
}
