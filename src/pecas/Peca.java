package pecas;

import jogo_xadrez.Tabuleiro;

public class Peca {
	
	private char cor;
	protected char caractere;
	protected int iPos, jPos;
	protected Tabuleiro t;
	
	public Peca(char cor, int iPos, int jPos, Tabuleiro t) {
		this.cor = cor;
		this.iPos = iPos;
		this.jPos = jPos;
		this.t = t;
	}
	
	public boolean mover(int id,int jd) { //recebe a posicao de destino da matriz
		return false;
	}
	
	public boolean mov_valido(int i,int j) {
		System.out.println("Isso nao era pra ser impresso");
		return false;
	}
	
	public char getCor(){
		return cor;
	}
	
	public char getCaractere() {
		return caractere;
	}
	
	public int getIPos() {
		return iPos;
	}
	
	public int getJPos() {
		return jPos;
	}

}
