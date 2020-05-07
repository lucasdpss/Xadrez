package pecas;

import jogo_xadrez.Tabuleiro;

public class Cavalo extends Peca {

	public Cavalo(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'h':'H';
	}
	
	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		
		if(id == iPos && jd == jPos) return false;  //Nao pode ir para o lugar atual
		
		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false; //Destino deve estar dentro do tabuleiro
		if(diferencaI == 0 || diferencaJ == 0) return false;
		if(diferencaI > 2 || diferencaJ > 2) return false;
		
		if(diferencaI == 1 && diferencaJ != 2) return false;
		else if(diferencaI == 2 && diferencaJ != 1) return false;
		
		Peca capturada = t.getPeca(id, jd);
		if(capturada != null && capturada.getCor() == this.getCor()) return false;
		
		return true;
	}
	

}
