package pecas;

import jogo_xadrez.Tabuleiro;

public class Rainha extends Peca {

	public Rainha(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'q':'Q';
	}

}
