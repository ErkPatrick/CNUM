package metodosNumericos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FalsaPosicao {
	static double a, b;
	static double x, y;
	static double precisao = 0.01;
	static boolean positivoA, positivoB, positivoY;
	static double inicioIntervalo = 0;
	static double fimIntervalo = 3;
	static double h = 0.5; //amplitude de passo
	static int linhas = (int) ((Math.abs(fimIntervalo-inicioIntervalo)/h) + 1); // a ideia é saber a quantidade linhas da tabela pelo inicio e fim do intervalo, com a amplitude h
	static double[][] tabela = new double[linhas][2]; 
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat deci = new DecimalFormat("0.0000");

	public static void main(String[] args) {
		System.out.println("Tabelamento:\n");
		System.out.println("    X    |    Y   ");
		System.out.println("===================");
		for(int i = 0; i < linhas; i++) {
			tabela[i][0] = inicioIntervalo;
			tabela[i][1] = f(tabela[i][0]);
			inicioIntervalo+=h;
			System.out.println("   " + tabela[i][0] + "   |" + "   " + tabela[i][1]  + "   ");
		}
		for(int i = 0; i < linhas-1; i++) {
			a = tabela[i][0];
			b = tabela[i + 1][0];
			
			positivoA = f(a) > 0;
			positivoB = f(b) > 0;
			
			if(positivoA != positivoB) {
				System.out.println("\n\nHá raiz entre o intervalos [ " + a + " ; " + b + " ]\n\n");
				System.out.println("Refinando pelo método da Falsa Posição...\n\n");
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
				System.out.println("\n\n------------------------------------------------------------------------------\n\n");
			}
		}
	}

	public static double f(double x) {
		double resultado = Math.pow(x, 3) - 9*x +5;
		return resultado;
	}
}
