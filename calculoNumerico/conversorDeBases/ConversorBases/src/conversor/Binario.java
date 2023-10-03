package conversor;

public class Binario {
	private String binario;
	public Binario() {}
	public String getBinario() {
		return binario;
	}
	public void setBinario(String binario) {
		if(binario != null && !binario.isEmpty())
			this.binario = binario;
		else
			System.out.println("Entrada inválida");
	}
	public String inverterString(String string) {
	    int tam = string.length();
	    char[] charArray = string.toCharArray();
	    for (int i = 0; i < tam / 2; i++) {
	        char ch = charArray[i];
	        charArray[i] = charArray[tam - 1 - i];
	        charArray[tam - 1 - i] = ch;
	    }
	    return new String(charArray);
	}
	public String conversorBinario(String base, String tipo) {
		binario = "";
		if(tipo.equals("decimal")) {
			int decimal = Integer.parseInt(base);;
			do {
			System.out.println("decimal =" + decimal);
			binario += decimal%2;
			System.out.println("Binario= " + binario);
			decimal = decimal/2;
			}while(decimal > 0);
			System.out.println("Passei por aqui");
			binario = inverterString(binario);
		}
		else if (tipo.equals("octal")) {
		    for (int i = 0; i < base.length(); i++) {
		        char digitoAtual = base.charAt(i);
		        switch (digitoAtual) {
		            case '0':
		                binario += "000";
		                break;
		            case '1':
		                binario += "001";
		                break;
		            case '2':
		                binario += "010";
		                break;
		            case '3':
		                binario += "011";
		                break;
		            case '4':
		                binario += "100";
		                break;
		            case '5':
		                binario += "101";
		                break;
		            case '6':
		                binario += "110";
		                break;
		            case '7':
		                binario += "111";
		                break;
		            default:
		                break;
		        }
		    }
		}
		else if(tipo.equals("hexadecimal")) {
			for (int i = 0; i < base.length(); i++) {
		        char digitoAtual = base.charAt(i);
		        switch (digitoAtual) {
	            case '0':
	                binario += "0000";
	                break;
	            case '1':
	                binario += "0001";
	                break;
	            case '2':
	                binario += "0010";
	                break;
	            case '3':
	                binario += "0011";
	                break;
	            case '4':
	                binario += "0100";
	                break;
	            case '5':
	                binario += "0101";
	                break;
	            case '6':
	                binario += "0110";
	                break;
	            case '7':
	                binario += "0111";
	                break;
	            case '8':
	                binario += "1000";
	                break;
	            case '9':
	                binario += "1001";
	                break;
	            case 'A':
	            case 'a':
	                binario += "1010";
	                break;
	            case 'B':
	            case 'b':
	                binario += "1011";
	                break;
	            case 'C':
	            case 'c':
	                binario += "1100";
	                break;
	            case 'D':
	            case 'd':
	                binario += "1101";
	                break;
	            case 'E':
	            case 'e':
	                binario += "1110";
	                break;
	            case 'F':
	            case 'f':
	                binario += "1111";
	                break;
		        }
			}
		}
		System.out.println("Cheguei no return");
		return binario;
	}
}
