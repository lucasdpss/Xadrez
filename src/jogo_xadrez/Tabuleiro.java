package jogo_xadrez;

import pecas.*;

public class Tabuleiro {
	private Peca matriz[][];
	private char lance;
	
	public Tabuleiro(){
		matriz = new Peca[8][8];
		lance = 'B';
		
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
}
