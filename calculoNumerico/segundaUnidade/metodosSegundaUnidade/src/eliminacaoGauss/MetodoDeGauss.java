package eliminacaoGauss;

import java.util.Scanner;

public class MetodoDeGauss {
	private static double [][] matrizA = new double [3][3];
	private static double [] vetorB = new double [3];
	private static double [][] matrizAB = new double [3][4];
	private static double [] vetorX = new double[3];
	private static boolean solUnica;
	
	
	public static double[][] valoresMatrizA() {
		Scanner scan = new Scanner (System.in);
		System.out.println("Informe a matriz dos coeficientes(A)");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.println("Digite o elemento a" + (i+1) + (j+1)+ ": ");
				matrizA[i][j] = scan.nextDouble();			}
		}
		return matrizA;
	}
	public static double[] valoresVetorB() {
		Scanner scan = new Scanner (System.in);
		System.out.println("Informe os valores do vetor B");
		for(int i = 0, j = 1; i<3; i++, j++) {
			System.out.println("Digite o elemento [" + j + "]");
			vetorB[i] = scan.nextDouble();
			}
		return vetorB;
	}
	public static double[][] formarMatrizAB(double[][] matrizA, double[]vetorB){
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				matrizAB[i][j] = matrizA[i][j];
			}
		}
		matrizAB[0][3] = vetorB[0];
		matrizAB[1][3] = vetorB[1];
		matrizAB[2][3] = vetorB[2];
		
		return matrizAB;
	}
		public static double[][] escalonamento(double[][]matrizAB){
			double pivo1 = matrizAB[0][0];
			if(pivo1 == 0) {
				//matrizAB = pivoteamento(matrizAB, 0);
				pivo1 = matrizAB[0][0];
			}
			double ml2 = matrizAB[1][0]/pivo1;
			if(ml2 != 0) {
				matrizAB[1][0] = matrizAB[1][0] - (ml2*matrizAB[0][0]);
				matrizAB[1][1] = matrizAB[1][1] - (ml2*matrizAB[0][1]);
				matrizAB[1][2] = matrizAB[1][2] - (ml2*matrizAB[0][2]);
				matrizAB[1][3] = matrizAB[1][3] - (ml2*matrizAB[0][3]);
			}
			double ml3 = matrizAB[2][0]/pivo1;
			if(ml3 != 0) {
				matrizAB[2][0] = matrizAB[2][0] - (ml3*matrizAB[0][0]);
				matrizAB[2][1] = matrizAB[2][1] - (ml3*matrizAB[0][1]);
				matrizAB[2][2] = matrizAB[2][2] - (ml3*matrizAB[0][2]);
				matrizAB[2][3] = matrizAB[2][3] - (ml3*matrizAB[0][3]);
			}
			//terminamos o primeiro passo do escalonamento de uma matriz 3x3
			//agora seguiremos para o último passo:
			double pivo2 = matrizAB[1][1];
			if(pivo2 == 0) {
				//matrizAB = pivoteamento(matrizAB, 1);
				pivo2 = matrizAB[1][1];
			}
			ml3 = matrizAB[2][1]/pivo2;
			if(ml3 != 0) {
				matrizAB[2][0] = matrizAB[2][0] -(ml3*matrizAB[1][0]);
				matrizAB[2][1] = matrizAB[2][1] -(ml3*matrizAB[1][1]);
				matrizAB[2][2] = matrizAB[2][2] -(ml3*matrizAB[1][2]);
				matrizAB[2][3] = matrizAB[2][3] -(ml3*matrizAB[1][3]);
			}
			return matrizAB;
		}
		public static boolean testeSolucaoUnica(double[][] matrizAB) {
			double determinante = matrizAB[0][0]*matrizAB[1][1]*matrizAB[2][2];
			return determinante != 0;
		}
		public static double[] retroSubs(double[][]matrizAB) {
				vetorX[2] = matrizAB[2][3]/matrizAB[2][2];
				vetorX[1] = (matrizAB[1][3]-(vetorX[2]*matrizAB[1][2]))/matrizAB[1][1];
				vetorX[0] = (matrizAB[0][3]-(matrizAB[0][2]*vetorX[2])-(matrizAB[0][1]*vetorX[1]))/matrizAB[0][0];
			return vetorX;
		}
	
	public static void main(String[] args) {
		
		System.out.println("Metodo de Gauss na matriz 3x3\n\n");
		matrizA = valoresMatrizA();
		vetorB = valoresVetorB();
		matrizAB = formarMatrizAB(matrizA, vetorB);
		matrizAB = escalonamento(matrizAB); //falta um toString
		solUnica = testeSolucaoUnica(matrizAB); // irá ser booleano;  // verificar se há solução única //verficação pela diagonal principal
		if(solUnica) {
			vetorX = retroSubs(matrizAB);
			System.out.println("Conjunto solução:\n\n");
			for(int i = 0, j = 1; i < 3; i++, j++) {
				System.out.printf("X"+j + "= %.5f %n", vetorX[i]);
			}
		}else
			System.out.println("A matriz não possuí solução única pois seu determinante é igual a 0");
	}
}