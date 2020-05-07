package jogo_xadrez;

import pecas.*;

public class Tabuleiro {
	private Peca matriz[][];
	private char lance;
	private boolean casa_ameacada[][];
	private boolean rei_branco_em_xeque;
	private boolean rei_preto_em_xeque;
	
	public Tabuleiro(){
		casa_ameacada = new boolean[8][8];
		matriz = new Peca[8][8];
		lance = 'B';
		rei_branco_em_xeque = false;
		rei_preto_em_xeque = false;
		
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if((i == 0) && (j == 7 || j == 0))
					matriz[i][j] = new Torre('P', i, j, this);
				else if((i == 7) && (j == 7 || j == 0))
					matriz[i][j] = new Torre('B', i, j, this);
				else if((i == 0) && (j == 6 || j == 1))
					matriz[i][j] = new Cavalo('P', i, j, this);
				else if((i == 7) && (j == 6 || j == 1))
					matriz[i][j] = new Cavalo('B', i, j, this);
				else if((i == 0) && (j == 5 || j == 2))
					matriz[i][j] = new Bispo('P', i, j, this);
				else if((i == 7) && (j == 5 || j == 2))
					matriz[i][j] = new Bispo('B', i, j, this);
				else if((i == 0) && (j == 4))
					matriz[i][j] = new Rei('P', i, j, this);
				else if((i == 7) && (j == 3))
					matriz[i][j] = new Rainha('B', i, j, this);
				else if((i == 0) && (j == 3))
					matriz[i][j] = new Rainha('P', i, j, this);
				else if((i == 7) && (j == 4))
					matriz[i][j] = new Rei('B', i, j, this);
				else if(i == 1)
					matriz[i][j] = new Peao('P', i, j, this);
				else if(i == 6)
					matriz[i][j] = new Peao('B', i, j, this);
			}
		}
	}


	public void mostrar() {
		for(int i=0;i < 8;i++) {
			System.out.print(8 - i);
			for(int j=0;j < 8;j++) {
				if(matriz[i][j] == null) {
					System.out.print(" -");
				}else {
					System.out.print(" " + matriz[i][j].getCaractere());
				}
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	//verifica as casas ameacadas e se algum rei esta em xeque para a cor dada
	public void analisa_jogo(char cor) {
		this.setRei_em_xeque('B', false);
		this.setRei_em_xeque('P', false);
		for(int i=0;i < 8;i++)
			for(int j=0;j < 8;j++) {
				casa_ameacada[i][j] = false;
			}
		
		//lembrando que ameaca posicoes somente se for uma peca inimiga
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(this.getPeca(i, j) != null && this.getPeca(i, j).getCor() != cor) //devemos verificar a ameaca das pecas
					this.getPeca(i, j).ameaca_posicoes();                            // de cor oposta a cor dada
			}
		}
				
	}
	
	public boolean xequeMate() { //testa xeque mate para o jogador do lance atual
		
		boolean foiXequeMate = true; //tera sido um xeque mate ate encontrar uma jogada que tira o xeque do rei
		
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(this.getPeca(i, j) != null && this.getPeca(i, j).getCor() == this.lance) {
					if(this.getPeca(i, j).algumMovimentoSalvaRei()) {
						foiXequeMate = false;
						return foiXequeMate;
					}
				}
			}
		}
		return foiXequeMate;
	}
	
	public Peca getPeca(int i, int j) {
		return matriz[i][j];
	}
	
	public void setPeca(int i, int j, Peca p) {
		matriz[i][j] = p;
	}
	
	public char getLance() {
		return lance;
	}
	
	public void mudaJogador() {
		lance = (lance == 'B')?'P':'B';
	}
	
	public boolean getRei_em_xeque(char cor) {
		if(cor == 'B') 
			return rei_branco_em_xeque;
		else 
			return rei_preto_em_xeque;
	}
	
	public void setRei_em_xeque(char cor,boolean em_xeque) {
		if(cor == 'B') 
			rei_branco_em_xeque = em_xeque;
		else 
			rei_preto_em_xeque = em_xeque;
	}
	
	public void setCasa_ameacada(int iPos,int jPos, boolean ameacada) {
		casa_ameacada[iPos][jPos] = ameacada;
	}
}
