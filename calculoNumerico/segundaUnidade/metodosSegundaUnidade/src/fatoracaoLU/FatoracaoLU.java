package fatoracaoLU;

import java.util.Scanner;

public class FatoracaoLU {
	static double [][]matrizA = new double[3][3];
	static double[][] matrizL = new double[3][3];
	static double[][] matrizU = new double[3][3];
	static double[] vetorB = new double[3];
	static double[] vetorX = new double[3];
	static double[] vetorY = new double[3];
	static double ml21, ml31, ml32;
	static Scanner scan = new Scanner (System.in);
	static boolean fatLUUnica;
	/*Exemplo:  3 + 2 + 4 = 1    x1 = -3 
	 * 			1 + 1 + 2 = 2	 x2 = 5
	 * 			4 + 3 - 2 = 3 	 x3 = 0 */
	
	
	public static void main(String[] args) {
		System.out.println("Método da Fatoração LU\n");
		matrizA = valoresMatrizA();
		System.out.println("Matriz A: \n");
		toStringMatriz(matrizA);
		vetorB = valoresVetorB();
		System.out.println("Vetor b: \n");
		toStringVetor(vetorB);
		matrizU = escalonamento(matrizA);
		System.out.println("Matriz Upper: \n");
		toStringMatriz(matrizU);
		fatLUUnica = verificadorLUUnica(matrizU); // verifica se a fatoração A = LU é única
		if(fatLUUnica) {	
			matrizL = formarMatrizL();
			System.out.println("Matriz Lower: \n");
			toStringMatriz(matrizL);
			vetorY = calcularY();
			System.out.println("Vetor Y: \n");
			toStringVetor(vetorY);
			vetorX = calcularX();
			System.out.println("Vetor X: \n");
			toStringVetor(vetorX);
		}
		else
			System.out.println("O determinante de todos os menores principais da matriz A é nulo, logo a fatoração A = LU não é única");
		
		
	}
	
	//================= Lendo os valores da MatrizA ====================================
	public static double[][] valoresMatrizA(){
		System.out.println("Informe os valores da matriz dos coeficientes(A)");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.printf("Digite o elemento a" + (i+1) + (j+1)+ ": ");
				matrizA[i][j] = scan.nextDouble();
				}
		}
		return matrizA;
	}
	//==================Lendo valores do vetorB====================================
	public static double[] valoresVetorB() {
		System.out.println("Informe, abaixo, os valores do vetor b\n\n");
		for(int i = 0, j = 1; i < 3; i++, j++) {
			System.out.printf("Digite o valor de b" + j + ": ");
			vetorB[i] = scan.nextDouble();
		}
		return vetorB;
	}
	//====================Escalonando a matrizA ===================================
	public static double[][] escalonamento(double[][]matrizA){
		double pivo1 = matrizA[0][0];
		if(pivo1==0) {
			//matrizA=pivoteamento(matrizA, vetorB);
			pivo1 = matrizA[0][0];
		}
		ml21 = matrizA[1][0]/pivo1;
		if(ml21 !=0) {
			matrizA[1][0] = matrizA[1][0] - (ml21*matrizA[0][0]);
			matrizA[1][1] = matrizA[1][1] - (ml21*matrizA[0][1]);
			matrizA[1][2] = matrizA[1][2] - (ml21*matrizA[0][2]);
		}
		ml31 = matrizA[2][0]/pivo1;
		if(ml31!=0) {
			matrizA[2][0] = matrizA[2][0] - (ml31*matrizA[0][0]);
			matrizA[2][1] = matrizA[2][1] - (ml31*matrizA[0][1]);
			matrizA[2][2] = matrizA[2][2] - (ml31*matrizA[0][2]);
		}
		double pivo2 = matrizA[1][1];
		if(pivo2==0) {
			//matrizA=pivoteamento(matrizA, vetorB);
			pivo2 = matrizA[1][1];
		}
		ml32 = matrizA[2][1]/pivo2;
		if(ml32!=0) {
			matrizA[2][0] = matrizA[2][0] -(ml32*matrizA[1][0]);
			matrizA[2][1] = matrizA[2][1] -(ml32*matrizA[1][1]);
			matrizA[2][2] = matrizA[2][2] -(ml32*matrizA[1][2]);
		}
		return matrizA;
	}
	//=================Verificar se a fatoração A = LU é única ======================
	public static boolean verificadorLUUnica(double[][] matrizU) {
		return (matrizU[0][0]*matrizU[1][1]*matrizU[2][2]) != 0;
	}
	//=======================formar a matriz L(Lower)==================================
	public static double [][] formarMatrizL(){
		for(int i = 0; i < 3; i++) {
			matrizL[i][i] = 1;
		}
		//zerando os campos acima da diagonal principal(triângular superior)
		matrizL[0][1] = 0;
		matrizL[0][2] = 0;
		matrizL[1][2] = 0;
		//preenchendo os elementos abaixo da diagonal principal com os multiplicadores de cada linha
		matrizL[1][0] = ml21;
		matrizL[2][0] = ml31;
		matrizL[2][1] = ml32;
		
		return matrizL;
	}
	//================Cálculo do y =================
	public static double[] calcularY(){
		vetorY[0] = vetorB[0];
		vetorY[1] = vetorB[1] - (matrizL[1][0]* vetorY[0]);
		vetorY[2] = vetorB[2] - (matrizL[2][0]*vetorY[0]) - (matrizL[2][1]*vetorY[1]);
	
		return vetorY;
	}
	//===============cálculo do x ==========================
	public static double[] calcularX() {
		vetorX[2] = vetorY[2]/matrizU[2][2];
		vetorX[1] = ((vetorY[1]-(matrizU[1][2]*vetorX[2]))/matrizU[1][1]);
		vetorX[0] = ((vetorY[0] - (matrizU[0][1]*vetorX[1]) - (matrizU[0][2]*vetorX[2]))/matrizU[0][0]);
	
		return vetorX;
	}
	
	//=============toString para visualizar as matrizes==================
	public static void toStringMatriz(double[][] matriz) {
		
		for(int i = 0; i < 3; i++) { // ver forma genêrica
			System.out.print("|");
			for(int j = 0; j < 3; j++) {
				System.out.printf("%.5f",matriz[i][j]);
				System.out.print(" ");
			}
			System.out.print("|\n");
		}
	}
	//=============toString para visualizar os vetores ============
	public static void toStringVetor(double[] vetor) {
		for(int i = 0; i < 3; i++) {
			System.out.print("|");
			System.out.printf("%.5f",vetor[i]);
			System.out.println("|");
		}
	}
	
	
}
