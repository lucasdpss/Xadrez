package jogo_xadrez;
import pecas.*;

public class Main {

	public static void main(String[] args) {
		CSVReader csv = new CSVReader();
		csv.setDataSource("src\\jogo.csv");
		String commands[] = csv.requestCommands();

		Tabuleiro t = new Tabuleiro();

		System.out.println();
	}
		
		

}

