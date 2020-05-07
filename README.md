# Xadrez
  Trabalho MC322 <br/>
  Nome do Grupo: Objetos Desorientados
  - Lucas de Paula Soares - RA: 201867<br/>
  - Antonio Gabriel da Silva Fernandes - RA: 231551

Os arquivos CSV que o programa lê como entrada estão na pasta \\jogos_exemplo. Lá já existe um exemplo de jogo no qual todos os movimentos entrados são válidos e um exemplo no qual há alguns movimentos inválidos inseridos, além de uma imagem mostrando o estado final dos exemplos.


## Pacotes
  Na pasta \\src estão 3 pacotes com as classes usadas:
### comandos
  Classes utilizada para a leitura de comandos, como o CSVReader e sua versão que suporta tipos heterogêneos de comandos (para mover peças ou para transformar um peão).
### jogo_xadrez
  Contém duas classes, Main (responsável pelo controle do fluxo do jogo) e Tabuleiro.
### pecas
  Contém a classe abstrata genérica Peca, e suas herdeiras que representam os diferentes tipos de peças que povoam o tabuleiro.
