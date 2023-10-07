package MetodoGaussJacobi;

import java.util.Scanner;

public class GaussJacobi {
	static double[][] matrizA = new double[3][3];
	static double[] vetorB = new double[3];
	static double[] vetorX = new double[3];
	static double[] vetorXAnterior = new double[3];
	static double precisao;
	static int limiteIteracoes;
	static int qtdIteracoes = 0;
	static double maiorAbs, maiorVetorX;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		diagonalmenteDominante();
		montarVetorB();
		System.out.printf("Digite a precisão: ");
		precisao = scan.nextDouble();
		System.out.printf("Digite o limite de iterações: ");
		limiteIteracoes = scan.nextInt();
		 do{ 
			iteracao();
			qtdIteracoes++;
		}while(!criterioParada());
		 System.out.println("Vetor X:");
		 for(int i = 0; i<3; i++) {
			 System.out.println("X" + i+1 +": " + vetorX[i]);
		 }
		 scan.close();
	}
	//==============verificando se a matriz A é diagonalmente Dominante pelo método das linhas e colunas================
	private static void diagonalmenteDominante() {
		System.out.println("Informe os valores da matriz dos coeficientes(A)");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.printf("Digite o elemento a" + (i+1) + (j+1)+ ": ");
				matrizA[i][j] = scan.nextDouble();
				}
		}
		if(metodoLinha() || metodoColuna()) {
			System.out.println("A matriz é diagonalmente dominante, ou seja, a convergência é garantida");
		}
		else
			System.out.println("A matriz não é diagonalmente dominante, ela pode convergir ou não");
	}
	private static boolean metodoLinha() { // errado
		boolean verificador = true;
		double somatorioLinha = 0;
		for(int i = 0; i <=3; i++){
			for(int j = 0; j<3; j++) {
				if(i!=j)
					somatorioLinha += matrizA[i][j];
			}
				verificador = matrizA[i][i] > somatorioLinha;
				if(!verificador)
					return false;
		}
		return verificador;
	}
	private static boolean metodoColuna() {
		boolean verificador = true;
		double somatorioColuna = 0;
		for(int i = 0; i <=3; i++){
			for(int j = 0; j<3; j++) {
				if(i!=j)
					somatorioColuna += matrizA[j][i];
			}
				verificador = matrizA[i][i] > somatorioColuna;
				if(!verificador)
					return false;
		}
		return verificador;
	}
	//==============montando o sistema que será iterado até a condição de parada======================
	private static void iteracao() {
		for(int i = 0; i < 3; i++) {
			vetorX[i] = vetorB[i];
			for(int j = 0; j < 3; j++) {
				if(i!=j)
					vetorX[i] += matrizA[i][j]*vetorX[j];
			}
			vetorX[i] = vetorX[i]/matrizA[i][i];
		}
	}
	//==========lendo valore para o vetor b===============
	private static void montarVetorB() {
		System.out.println("Informe, abaixo, os valores do vetor b\n\n");
		for(int i = 0, j = 1; i < 3; i++, j++) {
			System.out.printf("Digite o valor de b" + j + ": ");
			vetorB[i] = scan.nextDouble();
		}
	}
	//===================verifica o critério de parada===================
	private static boolean criterioParada() {
		for(int i = 0; i < 3; i++)
			vetorXAnterior[i] = vetorX[i];
		return criterioParadaDistanciaAbs() || criterioParadaDistanciaRel() ||criterioParadaLimiteIteracoes();
	}
	private static boolean criterioParadaDistanciaAbs() {
		maiorAbs = Math.abs(vetorX[0] - vetorXAnterior[0]);  
		boolean verificador;
		for(int i = 1; i < 3; i++) {
			if(maiorAbs < Math.abs(vetorX[i] - vetorXAnterior[i])) {
				maiorAbs = Math.abs(vetorX[i] - vetorXAnterior[i]);
			}
		}
		
		if(maiorAbs < precisao) {
			verificador = true;
			System.out.println("O sistema atingiu o critério de parada de distância absoluta na " + qtdIteracoes + "ª iteração");
		}
		else
			verificador = false;
		
		return verificador;
	}
	private static boolean criterioParadaDistanciaRel() {
		boolean verificador;
		maiorVetorX = vetorX[0];
		for(int i = 1; i<3; i++) {
			if(maiorVetorX<vetorX[i])
				maiorVetorX = vetorX[i];
		}
		if(maiorAbs/maiorVetorX < precisao) {
			verificador = true;
			System.out.println("O sistema atingiu o critério de parada de distância relativa na " + qtdIteracoes + "ª iteração");
		}
		else verificador = false;
		
		
		return verificador;
	}
	private static boolean criterioParadaLimiteIteracoes() {
		boolean verificador = false;
		if(qtdIteracoes==limiteIteracoes) {
			verificador = true;
			System.out.println("O sistema atingiu o limite de iterações(" + limiteIteracoes + " iterações)");
		}
		return verificador;
	}
	
}
