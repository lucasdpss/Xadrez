package pecas;

import jogo_xadrez.Tabuleiro;

public class Torre extends Peca {

	public Torre(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'t':'T';
	}

}
