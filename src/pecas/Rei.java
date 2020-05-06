package pecas;

import jogo_xadrez.Tabuleiro;

public class Rei extends Peca{

	public Rei(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'k':'K';
	}
	
	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		
		if(id == iPos && jd == jPos) return false;  //Nao pode ir para o lugar atual

		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false;
		if(diferencaI == 0 && diferencaJ == 0) return false;
		if(diferencaI > 1 || diferencaJ > 1) return false;
		
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

}
