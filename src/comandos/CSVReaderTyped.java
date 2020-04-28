package comandos;

public class CSVReaderTyped extends CSVReader {
	private Comando[] commands;
	
	public CSVReaderTyped() {
		super();
	}
	
	public Comando[] requestCommandsTyped() {
		String[] commandsStr = super.requestCommands();
		commands = new Comando[commandsStr.length];
		
		for(int i=0;i < commandsStr.length ; i++) {
			if(commandsStr[i].length() == 5)
				commands[i] = new Movimento(commandsStr[i]);
			else if(commandsStr[i].length() == 1)
				commands[i] = new Transforma(commandsStr[i]);
			else System.out.println("Entrada inválida na célula " + i);
		}
		return null;
	}
	
}
