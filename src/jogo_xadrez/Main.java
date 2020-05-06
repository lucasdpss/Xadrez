package jogo_xadrez;
import pecas.*;
import comandos.*;

public class Main {

	public static void main(String[] args) {
		CSVReaderTyped csv = new CSVReaderTyped();
		csv.setDataSource("src\\comandos\\jogo.csv");
		Comando commands[] = csv.requestCommandsTyped();

		Tabuleiro t = new Tabuleiro();
		
		t.mostrar();
		System.out.println();
		
		Peca pecaAtual = null;
		
		for(int i=0;i < commands.length;i++) {
			if(commands[i].getText().length() == 5) { //Movimento
				System.out.println("Source: "+commands[i].getText().charAt(0)+commands[i].getText().charAt(1));
				System.out.println("target: "+commands[i].getText().charAt(3)+commands[i].getText().charAt(4));
				
				int ii = '8' - commands[i].getText().charAt(1);    //i inicial
				int ji =  commands[i].getText().charAt(0) - 'a';   //j inicial
				int id = '8' - commands[i].getText().charAt(4);    //i destino
				int jd = commands[i].getText().charAt(3) - 'a';    //j destino
				
				pecaAtual = t.getPeca(ii, ji);
				
				if(pecaAtual != null && pecaAtual.getCor() == t.getLance()) {
					pecaAtual.mover(id, jd);
					t.mudaJogador();
				}
				
				t.mostrar();
				System.out.println();
			}
			
			//TRANSFORMACAO DE PEOES NAO FOI TESTADA
			else if(commands[i].getText().length() == 1) { //Transformacao de peao
				Peca ultimaPeca = pecaAtual;
				
				if((ultimaPeca.getCaractere() == 'p' && ultimaPeca.getIPos() == 0)
				|| (ultimaPeca.getCaractere() == 'P' && ultimaPeca.getIPos() == 7)) {
					switch(commands[i].getText()) {
					case "t":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Torre(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "h":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Cavalo(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "b":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Bispo(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "q":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Rainha(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
						
					case "T":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Torre(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "H":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Cavalo(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "B":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Bispo(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					case "Q":
						t.setPeca(ultimaPeca.getIPos(), ultimaPeca.getJPos(), new Rainha(ultimaPeca.getCor(), ultimaPeca.getIPos(), ultimaPeca.getJPos(), t));
						break;
					}
				}
				
			}
		}
	}
		
		

}

