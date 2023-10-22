package metodosNumericos;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PontoFixo {
	static double a, b;
	static double x, y;
	static double precisao = 0.01;
	static int iteracao = 0;
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
			
			if (positivoA != positivoB) {
				System.out.println("\n\nHá raiz entre o intervalos [ " + a + " ; " + b + " ]\n\n");
				System.out.println("Refinando pelo método do Ponto Fixo...\n\n");
				System.out.println("____________________________________");
				System.out.println("| ITER. |      X     |      f(x)   |");
				System.out.println("------------------------------------");
				iteracao = 0; // devemos reiniciar a iteração a cada novo refinamento de raiz
				do {
					if(iteracao == 0)
						x = (a + b)/2; // x0
					else
						x = p(x); // método do ponto fixo
					
					y = f(x);
					System.out.println("|   " + iteracao + "   |" + "   " + deci.format(x) + "   |" + "   " + deci.format(y) + "   |");
					iteracao++;
				} while (Math.abs(y) > precisao);
				System.out.println("====================================");
				System.out.println("Para uma precisão de " + precisao + " temos:");
				System.out.println("Raiz = " + deci.format(x) + "\nf(x) = " + deci.format(y));
				System.out.println("\n\n----------------------------------\n\n");
			}
		}
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
