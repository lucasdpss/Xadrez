package pecas;

import jogo_xadrez.Tabuleiro;

public class Rainha extends Peca {

	public Rainha(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'q':'Q';
	}
	
	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		
		if(id == iPos && jd == jPos) return false;  //Nao pode ir para o lugar atual
		char movimento;

		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false;
		
		if(diferencaI == 0 && diferencaJ == 0) return false;
		
		if(diferencaI != 0) {
			if(diferencaJ == 0) movimento = 'V'; //movimento vertical
			else if(diferencaJ == diferencaI) movimento = 'D'; //movimento em diagonal
			else return false;
		}
		else movimento = 'H'; //movimento horizontal
		
		switch(movimento) {
		case 'V':
			for(int v=1;v < diferencaI;v++)
				if(t.getPeca(iPos + ((id-iPos)>0?v:-v), jPos) != null)
					return false;
			break;
		case 'H':
			for(int v=1;v < diferencaI;v++)
				if(t.getPeca(iPos, jPos + ((jd-jPos)>0?v:-v)) != null)
					return false;
			break;
		case 'D':
			for(int v=1;v < diferencaI;v++)
				if(t.getPeca(iPos + ((id-iPos)>0?v:-v), jPos + ((jd-jPos)>0?v:-v)) != null)
					return false;
			break;
		}
		
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
