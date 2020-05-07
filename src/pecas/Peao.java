package pecas;

import jogo_xadrez.Tabuleiro;

public class Peao extends Peca{
	
	private boolean primeiroMovimento;

	public Peao(char cor, int iPos, int jPos, Tabuleiro t) {
		super(cor, iPos, jPos, t);
		caractere = (cor == 'B')?'p':'P';
		primeiroMovimento = true;
	}
	
	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		
		if(id == iPos && jd == jPos) return false;  //Nao pode ir para o lugar atual
		
		if(this.getCor() == 'B' && id > iPos) return false; //Nao pode ir pra tras
		if(this.getCor() == 'P' && id < iPos) return false;
		
		if(diferencaI > 2) return false;
		if(diferencaJ > 1) return false;
		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false; //Destino deve estar dentro do tabuleiro
		
		if(diferencaI == 2) {
			if(diferencaJ != 0) return false;
			if(!primeiroMovimento) return false;
			if(t.getPeca((id+iPos)/2, jd) != null) return false;
		}
		
		else {
			if(diferencaJ == 0 && t.getPeca(id, jd) != null) return false;
			else if(diferencaJ == 1) {
				Peca capturada = t.getPeca(id, jd); 
				if(capturada == null) return false; //Precisa ter uma peca a ser capturada
				if(capturada.getCor() == this.getCor()) return false; //Essa peca precisa ser de cor diferente
			}
		}
		
		return true;
	}
	
	public boolean mover(int id, int jd) {
		if(this.mov_valido(id, jd)) {
			t.setPeca(id, jd, this);
			t.setPeca(iPos, jPos, null);
			iPos = id;
			jPos = jd;
			
			if(primeiroMovimento) primeiroMovimento = false;
			
			return true;
		}
		else return false;
	}
	
	private boolean ameaca_valida(int i,int j) {
		int diferencaI = (i-iPos>=0)?(i-iPos):(iPos-i);
		int diferencaJ = (j-jPos>=0)?(j-jPos):(jPos-j);
		
		if(i == iPos && j == jPos) return false;  //Nao pode ir para o lugar atual
		
		if(this.getCor() == 'B' && i > iPos) return false; //Nao pode ir pra tras
		if(this.getCor() == 'P' && i < iPos) return false;
		
		if(diferencaJ == 1 && diferencaI == 1) return true;
		else return false;
	}
	
	public void ameaca_posicoes() {
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(this.ameaca_valida(i, j)) {
					t.setCasa_ameacada(i, j, true);
					t.peca_atacante[i][j] = this.getCaractere();
					if(t.getPeca(i, j) != null && t.getPeca(i, j).getCaractere() == 'k')
						t.setRei_em_xeque('B', true);
					else if(t.getPeca(i, j) != null && t.getPeca(i, j).getCaractere() == 'K')
						t.setRei_em_xeque('P', true);
				}
			}
		}
	}
	

}
