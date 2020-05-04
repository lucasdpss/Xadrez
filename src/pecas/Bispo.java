package pecas;

import jogo_xadrez.Tabuleiro;

public class Bispo extends Peca {

	public Bispo(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'b':'B';
	}

	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);

		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false;
		if(diferencaI != diferencaJ) return false;
		if(diferencaI == 0) return false;

		for(int v=1;v < diferencaI;v++)
			if(t.getPeca(iPos + ((id-iPos)>0?v:-v), jPos + ((jd-jPos)>0?v:-v)) != null)
				return false;
		
		Peca capturada = t.getPeca(id, jd);
		if(capturada != null && capturada.getCor() == this.getCor()) return false;
		
		return true;
	}

	public boolean mover(int id, int jd) {
		if(this.mov_valido(id, jd)) {
			t.setPeca(id, jd, this);
			t.setPeca(iPos, jPos, null);
			iPos = id;
			jPos = jd;

			return true;
		}
		else return false;
	}
}
