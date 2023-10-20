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
	/* exemplo: 10 + 2 + 1 = 7
	 * 			1 + 5 + 1 = -8
	 * 			2 + 3 + 10 = 6*/
	public static void main(String[] args) {
		System.out.println("Método de Gauss Jacobi\n");
		formarMatrizA();
		diagonalmenteDominante();
		montarVetorB();
		System.out.printf("Digite a precisão: ");
		precisao = scan.nextDouble();
		System.out.printf("Digite o limite de iterações: ");
		limiteIteracoes = scan.nextInt();
		//vetor X0 recebe, já inicialmente bi/aii
		for(int i = 0; i < 3; i++)
			vetorX[i] = vetorB[i]/matrizA[i][i];
		
		 do{ 
			iteracao();
			qtdIteracoes++;
		}while(!criterioParada());
		 toStringVetorX();
		 scan.close();
	}
	//==============verificando se a matriz A é diagonalmente Dominante pelo método das linhas e colunas================
	public static void formarMatrizA(){
		System.out.println("Informe os valores da matriz dos coeficientes(A)");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.printf("Digite o elemento a" + (i+1) + (j+1)+ ": ");
				matrizA[i][j] = scan.nextDouble();
				}
		}
	}
	private static void diagonalmenteDominante() {
		if(metodoLinha() || metodoColuna()) {
			System.out.println("\nA matriz é diagonalmente dominante, ou seja, a convergência é garantida na iteração n");
		}
		else
			System.out.println("\nA matriz não é diagonalmente dominante, ela pode convergir ou não");
	}
	private static boolean metodoLinha() { 
		boolean verificador = true;
		for(int i = 0; i < 3; i++){
			double somatorioLinha = 0;
			for(int j = 0; j < 3; j++) {
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
		for(int i = 0; i < 3; i++){
			double somatorioColuna = 0;
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
	//==========lendo valore para o vetor b===============
		private static void montarVetorB() {
			System.out.println("\nInforme, abaixo, os valores do vetor b\n\n");
			for(int i = 0, j = 1; i < 3; i++, j++) {
				System.out.printf("Digite o valor de b" + j + ": ");
				vetorB[i] = scan.nextDouble();
			}
		}
	//==============montando o sistema que será iterado até a condição de parada======================
	private static void iteracao() {
		
		for(int i = 0; i < 3; i++)
			vetorXAnterior[i] = vetorX[i];
		
		for(int i = 0; i < 3; i++) {
			vetorX[i] = vetorB[i];
			for(int j = 0; j < 3; j++) {
				if(i!=j)
					vetorX[i] += -matrizA[i][j]*vetorXAnterior[j];
			}
			vetorX[i] = vetorX[i]/matrizA[i][i];
		}
	}
	//===================verifica o critério de parada===================
	private static boolean criterioParada() {
		return criterioParadaDistanciaAbs() || criterioParadaDistanciaRel() ||criterioParadaLimiteIteracoes();
	}
	private static boolean criterioParadaDistanciaAbs() {
		maiorAbs = Math.abs(vetorX[0] - vetorXAnterior[0]);  
		for(int i = 1; i < 3; i++) {
			if(maiorAbs < Math.abs(vetorX[i] - vetorXAnterior[i])) {
				maiorAbs = Math.abs(vetorX[i] - vetorXAnterior[i]);
			}
		}
		boolean verificador;
		if(maiorAbs < precisao) {
			verificador = true;
			System.out.println("\nO sistema atingiu o critério de parada de distância absoluta com um erro absoluto de "+maiorAbs+" na " + qtdIteracoes + "ª iteração");
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
		double erroRel = maiorAbs/maiorVetorX;
		if(erroRel < precisao) {
			verificador = true;
			System.out.println("O sistema atingiu o critério de parada em uma distância relativa de "+ erroRel+ " na " + qtdIteracoes + "ª iteração");
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
	public static void toStringVetorX() {
		System.out.println("Vetor X:");
		 for(int i = 0; i<3; i++) {
			 System.out.println("X" + (i+1) +": " + vetorX[i]);
		 }
	}
	
}