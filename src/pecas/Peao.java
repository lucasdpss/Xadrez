package pecas;

import jogo_xadrez.Tabuleiro;

public class Peao extends Peca{

	public Peao(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'p':'P';
	}

}
