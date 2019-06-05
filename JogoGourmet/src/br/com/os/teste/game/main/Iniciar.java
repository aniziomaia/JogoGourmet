package br.com.os.teste.game.main;

import br.com.os.teste.game.arvore.ArvoreAlimento;
import br.com.os.teste.game.logica.Game;
import br.com.os.teste.game.tela.Tela;
import br.com.os.teste.game.util.Constantes;

/**
 * Classe responsavel por iniciar o jogo
 * @author aniziomaia
 *
 */

public class Iniciar{
	
	public static void main(String[] args) {
		
		//cria a arvo que inicia o jogo com dois alimentos
		ArvoreAlimento arvoreRaiz = new ArvoreAlimento(Constantes.PERGUNTA_RAIZ);
		arvoreRaiz.setNoEsquerdo(new ArvoreAlimento(Constantes.PRATO_MASSA));
		arvoreRaiz.setNoDireito(new ArvoreAlimento(Constantes.RATO_BOLO));
		
		//seta os parametros para inicio do jogo
		Game game = new Game();
		game.setArvoreAlimento(arvoreRaiz);

		//adiciona do jogo na tela para ser iniciado
		new Tela().setGame(game);
	}
}
