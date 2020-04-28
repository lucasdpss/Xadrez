package pecas;

import jogo_xadrez.Tabuleiro;

public class Bispo extends Peca {

	public Bispo(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'b':'B';
	}

}
