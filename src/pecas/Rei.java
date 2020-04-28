package pecas;

import jogo_xadrez.Tabuleiro;

public class Rei extends Peca{

	public Rei(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'k':'K';
	}

}
