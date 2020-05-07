package pecas;

import jogo_xadrez.Tabuleiro;

public abstract class Peca {
	
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
	
	public abstract boolean mover(int id,int jd); //recebe a posicao de destino da matriz
	
	public abstract boolean mov_valido(int id,int jd) ;
	
	public void ameaca_posicoes() {
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(this.mov_valido(i, j)) {
					t.setCasa_ameacada(i, j, true);
					if(t.getPeca(i, j) != null && t.getPeca(i, j).getCaractere() == 'k')
						t.setRei_em_xeque('B', true);
					else if(t.getPeca(i, j) != null && t.getPeca(i, j).getCaractere() == 'K')
						t.setRei_em_xeque('P', true);
				}
			}
		}
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
