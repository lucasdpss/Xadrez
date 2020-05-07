package jogo_xadrez;
import pecas.*;
import comandos.*;

public class Main {

	public static void main(String[] args) {
		CSVReaderTyped csv = new CSVReaderTyped();
		csv.setDataSource("src\\comandos\\jogo2.csv");
		Comando commands[] = csv.requestCommandsTyped();

		Tabuleiro t = new Tabuleiro();
		
		t.mostrar();
		System.out.println();
		
		Peca pecaAtual = null;
		Peca pecaDestino = null;
		
		for(int i=0;i < commands.length;i++) {
			if(commands[i].getText().length() == 5) { //Movimento comum
				System.out.println("Source: "+commands[i].getText().charAt(0)+commands[i].getText().charAt(1));
				System.out.println("target: "+commands[i].getText().charAt(3)+commands[i].getText().charAt(4));
				System.out.println();
				
				int ii = '8' - commands[i].getText().charAt(1);    //i inicial
				int ji =  commands[i].getText().charAt(0) - 'a';   //j inicial
				int id = '8' - commands[i].getText().charAt(4);    //i destino
				int jd = commands[i].getText().charAt(3) - 'a';    //j destino
				
				if(ii >= 8 || ji >= 8 || ii < 0 || ji < 0) {
					System.out.println("Nao existe essa posicao no tabuleiro");
					continue;
				}
				if(id >= 8 || jd >= 8 || id < 0 || jd < 0) {
					System.out.println("Nao existe essa posicao destino no tabuleiro");
					continue;
				}
				
				pecaAtual = t.getPeca(ii, ji);
				pecaDestino = t.getPeca(id, jd);
				
				if(pecaAtual != null && pecaAtual.getCor() == t.getLance()) {
					
					
					if(pecaAtual.mover(id, jd)) {  //se o movimento for bem sucedido
						t.analisa_jogo(t.getLance()); //verifica as casas ameacadas
						if(t.getRei_em_xeque(t.getLance())) { //se o rei for ameacado nessa jogada
							System.out.println("Rei fica ameacado, movimento requerido proibido");
							t.setPeca(ii, ji, pecaAtual);  //volta a peca que tentou mexer
							t.setPeca(id, jd, pecaDestino); //volta a peca antiga (podendo ser null) para o lugar original
						}else {
							t.mudaJogador();
						}
					}else {
						System.out.println("Esse movimento nao é possível");
					}
					
				}else if(pecaAtual == null) {
					System.out.println("Nao ha peca nessa posicao");
				}else if(pecaAtual.getCor() != t.getLance()) {
					System.out.println("Nao esta na sua vez");
				}
				
			}
			
			else if(commands[i].getText().length() == 1) { //Transformacao de peao
				Peca ultimaPeca = pecaAtual;
				
				if((ultimaPeca.getCaractere() == 'p' && ultimaPeca.getIPos() == 0)
				|| (ultimaPeca.getCaractere() == 'P' && ultimaPeca.getIPos() == 7)) {
					System.out.println("Promoção do Peão:");
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
			
			t.mostrar();
			System.out.println();
			
			//analisar o jogo ao final da jogada: se foi apenas um xeque ou um xeque-mate para o prox jogador
			if(t.xequeMate()) {
				System.out.println("Xeque Mate!");
				break;
			}else {
				t.analisa_jogo(t.getLance());
				boolean reiEmXeque = t.getRei_em_xeque(t.getLance());
				if(reiEmXeque) {
					if(t.getLance() == 'B')
						System.out.println("Rei das Brancas em Xeque");
					else                                              
						System.out.println("Rei das Pretas em Xeque");
				}
			}
			
		}
	}
		
		

}

