package integracaoNumerica;

import java.util.Scanner;

public class SomaDeRiemann {
	static double somaDeRiemann, iniIntervalo, fimIntervalo, h, x;
	static double valorDaIntegral = 2.3333; //intervalo de 1 a 2
	static int qtdParticoes = 1000;
	static double [][] tabela = new double [qtdParticoes+1][2];
	static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String args[]) {
		intervalo();
		tabelamento();
		toStringTabela();
		esquerda();
		System.out.println("====================================");
		direita();
		
	}
	//=============lendo o intervalo ====================
	public static void intervalo() {
		System.out.printf("Qual o início do intervalo: ");
		iniIntervalo = scan.nextDouble();
		System.out.printf("Qual o fim do intervalo: ");
		fimIntervalo = scan.nextDouble();
	}
	//============fazendo o intervalo ==================
	public static void tabelamento() {
		h = (fimIntervalo - iniIntervalo)/qtdParticoes; // cálculo do h
		x = iniIntervalo;
		for(int i = 0; i<=qtdParticoes; i++) {
			tabela[i][0] = x;
			tabela[i][1] = Math.pow(x, 2); // colocamos a função aqui
			x+=h;
		}
	}
	//===============abordagem do c à esquerda ====================
	public static void esquerda() {
		somaDeRiemann = 0;
		for(int i = 0; i <= (qtdParticoes-1); i++) {
			somaDeRiemann += h*(tabela[i][1]);
		}
		System.out.println("A soma de Riemann à esquerda com " + qtdParticoes + " partições em um intervalo de " + iniIntervalo + " até " + fimIntervalo +  " é de: " + somaDeRiemann);
		calculoErro();
	}
	public static void direita() {
		somaDeRiemann = 0;
		for(int i = 1; i <= qtdParticoes; i++) {
			somaDeRiemann += h*(tabela[i][1]);
		}
		System.out.println("A soma de Riemann à direita com " + qtdParticoes + " partições em um intervalo de " + iniIntervalo + " até " + fimIntervalo +  " é de: " + somaDeRiemann);
		calculoErro();
	}
	public static void calculoErro() {
		double erroAbs, erroRel;
		erroAbs = Math.abs(somaDeRiemann-valorDaIntegral);
		System.out.println("Erro absoluto: " + erroAbs);
		erroRel = (erroAbs/valorDaIntegral)*100;
		System.out.println("Erro relativo = " + erroRel +"%");
	}
	public static void toStringTabela() {
		System.out.println("\tX\t|\tY\t");
		System.out.println("-------------------------------------");
		for(int linha = 0; linha <= qtdParticoes; linha++) {
			for(int coluna = 0; coluna < 2; coluna++) {
				System.out.printf(tabela[linha][coluna] + "\t"); 
			}
			System.out.println();
		}
		System.out.println("======================================================================================");
	}
}
