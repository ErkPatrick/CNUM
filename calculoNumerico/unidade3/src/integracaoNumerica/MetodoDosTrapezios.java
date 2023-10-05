package integracaoNumerica;

import java.util.Scanner;

public class MetodoDosTrapezios {
	static double metTrapezios, somTrapeziosIntermediarios, iniIntervalo, fimIntervalo, h, x;
	static double valorDaIntegral = 1.0986;
	static int qtdParticoes = 1000;
	static double [][] tabela = new double [qtdParticoes+1][2]; //muito confuso
	static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String args[]) {
		intervalo();
		tabelamento();
		toStringTabela();
		System.out.println("====================================");
		somTrapezios();
		
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
			tabela[i][1] = 1/x; // colocamos a função aqui
			x+=h;
		}
	}
	//===============cálculo do somatório dos trapézios ====================
	public static void somTrapezios() {

		for(int i = 1; i<=(qtdParticoes-1); i++) {
			somTrapeziosIntermediarios += (tabela[i][1]);
		}
		metTrapezios = h/2*(tabela[0][1]+(2*somTrapeziosIntermediarios)+tabela[qtdParticoes][1]);
		System.out.println("O somatório das áreas dos trapézios é igual a: " + metTrapezios);
			
		calculoErro();
	}
	//===============calcular erro===================
	public static void calculoErro() {
		double erroAbs, erroRel;
		erroAbs = Math.abs(metTrapezios-valorDaIntegral);
		System.out.println("Erro absoluto: " + erroAbs);
		erroRel = (erroAbs/valorDaIntegral)*100;
		System.out.println("Erro relativo = " + erroRel +"%");
	}
	public static void toStringTabela() {
		System.out.println("\tX\t|\tY\t");
		System.out.println("-------------------------------------");
		for(int linha = 0; linha < qtdParticoes+1; linha++) {
			for(int coluna = 0; coluna < 2; coluna++) {
				System.out.printf(tabela[linha][coluna] + "\t"); 
			}
			System.out.println();
		}
		//System.out.println("======================================================================================");
	}
}

