package comandos;

public class Comando {
	protected String text;
	
	public Comando(String text){
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
