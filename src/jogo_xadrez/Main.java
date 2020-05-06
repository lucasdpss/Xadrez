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
			if(commands[i].getText().length() == 5) { //Movimento comum
				System.out.println("Source: "+commands[i].getText().charAt(0)+commands[i].getText().charAt(1));
				System.out.println("target: "+commands[i].getText().charAt(3)+commands[i].getText().charAt(4));
				
				int ii = '8' - commands[i].getText().charAt(1);    //i inicial
				int ji =  commands[i].getText().charAt(0) - 'a';   //j inicial
				int id = '8' - commands[i].getText().charAt(4);    //i destino
				int jd = commands[i].getText().charAt(3) - 'a';    //j destino
				
				pecaAtual = t.getPeca(ii, ji);
				
				if(pecaAtual != null && pecaAtual.getCor() == t.getLance()) {
					t.analisa_jogo(); //verifica casas ameacadas para o jogador do lance atual e se tem rei em xeque
					
					if(t.getRei_em_xeque(t.getLance())) {
						System.out.println("Rei em xeque");
					}
					if(pecaAtual.mover(id, jd)) {  //se o movimento for bem sucedido
						System.out.println();
						t.analisa_jogo();
						if(t.getRei_em_xeque(t.getLance())) { //se o rei for ameacado nessa jogada
							System.out.println("Rei eh ameacado, movimento requerido proibido ******************************************");
							t.setPeca(ii, ji, pecaAtual);
							t.setPeca(id, jd, null); //volta a peca para o lugar original
						}else {
							t.mudaJogador();
						}
					}
				}else 
					System.out.println("movimento invalido #################################@#@#@#@#@############@#@#@##@#########");
				
				t.mostrar();
				System.out.println();
			}
			
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

