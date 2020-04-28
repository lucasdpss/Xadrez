package pecas;

import jogo_xadrez.Tabuleiro;

public class Cavalo extends Peca {

	public Cavalo(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'h':'H';
	}

}
