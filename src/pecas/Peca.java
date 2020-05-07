package pecas;

import jogo_xadrez.Tabuleiro;

public abstract class Peca {
	
	private char cor;
	protected char caractere;
	protected int iPos, jPos;
	protected Tabuleiro t;
	
	public Peca(char cor, int iPos, int jPos, Tabuleiro t) {
		this.cor = cor;
		this.iPos = iPos;
		this.jPos = jPos;
		this.t = t;
	}
	
	public abstract boolean mov_valido(int id,int jd);
	
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
	
	public boolean algumMovimentoSalvaRei() {
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				Peca pecaDestino = t.getPeca(i, j);
				int ii = this.getIPos();
				int ji = this.getJPos();
				if(this.mover(i, j)) {  //se o movimento for bem sucedido para a pecaDestino
					t.analisa_jogo(t.getLance()); //verifica as casas ameacadas
					if(t.getRei_em_xeque(t.getLance()) == false) { //se o rei nao for ameacado nessa jogada
						t.setPeca(ii, ji, this);  //volta a peca que tentou mexer
						t.setPeca(i, j, pecaDestino); //volta a peca antiga (podendo ser null) para o lugar original
						this.iPos = ii;
						this.jPos = ji;
						//System.out.println("peca que salva: "+ this.getCaractere()+" destino dela:" + i+" " +j); //debug
						return true; //esse movimento salvou o rei
					}
					t.setPeca(ii, ji, this);  //volta a peca que tentou mexer
					t.setPeca(i, j, pecaDestino); //volta a peca antiga (podendo ser null) para o lugar original
					this.iPos = ii;
					this.jPos = ji;
				}
			}
		}
		return false;
	}
	
	public char getCor(){
		return cor;
	}
	
	public char getCaractere() {
		return caractere;
	}
	
	public int getIPos() {
		return iPos;
	}
	
	public int getJPos() {
		return jPos;
	}

}
