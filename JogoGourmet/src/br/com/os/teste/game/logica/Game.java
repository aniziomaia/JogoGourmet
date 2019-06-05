package br.com.os.teste.game.logica;

import javax.swing.JOptionPane;

import br.com.os.teste.game.arvore.ArvoreAlimento;
import br.com.os.teste.game.tela.Tela;
import br.com.os.teste.game.util.Constantes;

/**
 * Classe responsavel por fazer as perguntas
 * e analizar as repostas escolhidas pelo usuario
 * 
 * @author aniziomaia
 *
 */
public class Game  {
	
	private Tela tela;
	private ArvoreAlimento arvoreAimento;

	public Game() {}
	
	public ArvoreAlimento getArvoreAlimento() {
		return arvoreAimento;
	}

	public void setArvoreAlimento(ArvoreAlimento arvoreAlimento) {
		this.arvoreAimento = arvoreAlimento;
	}

	/**
	 * metodo responsavel por percorrer os nos
	 * da arvore e fazer as perguntas equanto tiver noh
	 * este metodo sera invocado quando o botao ok for acionado
	 */
	public void fazerPerguntas() {
		
		ArvoreAlimento arvoreAlimentoCorrente = arvoreAimento;
		
		while (arvoreAlimentoCorrente.existeNo()) {
			//faz a primeia pergunta
			int resposta = tela.exibirTelaPergunta(Constantes.PERGUNTA_PRATO + arvoreAlimentoCorrente.getNome() + " ?");
			
			//recupera os pratos correspondentes a resposta
			if (resposta == JOptionPane.YES_OPTION) {
				arvoreAlimentoCorrente = arvoreAlimentoCorrente.getNoEsquerdo();
			
			} else if (resposta == JOptionPane.NO_OPTION) {
				arvoreAlimentoCorrente = arvoreAlimentoCorrente.getNoDireito();
			} 
		}
		
		this.fazSegundaPergunta(arvoreAlimentoCorrente);
	}

	/**
	 * faz a segunda pergunta e analisa a resposta
	 * caso tenha acertado a resposta aparece mensagem de acerto
	 * caso nao acertado o prato eh adicionado ao noh
	 * @param prato
	 */
	private void fazSegundaPergunta(ArvoreAlimento prato) {
		
		int resposta = tela.exibirTelaPergunta(Constantes.PERGUNTA_PRATO_E_UM + prato.getNome() + " ?");
		
		if (resposta == JOptionPane.YES_OPTION) {
			//verifica se foi o primeiro acerto da maquina
			if(Constantes.ACERTOS_MAQUINA == 0) {
				Constantes.ACERTOS_MAQUINA ++;
				String msg = new String(Constantes.PRIMEIRO_ACERTO.replace("?", ""+Constantes.ACERTOS_MAQUINA));
				msg = msg.replace("#", ""+Constantes.ACERTOS_JOGADOR);
				tela.exibirTelaMensagem(msg);
			}else {
				Constantes.ACERTOS_MAQUINA ++;
				String msg = new String(Constantes.MAIS_DE_UM_ACERTO.replace("?", ""+Constantes.ACERTOS_MAQUINA));
				msg = msg.replace("#", ""+Constantes.ACERTOS_JOGADOR);
				tela.exibirTelaMensagem(msg);
			}
			
		//caso a resposta seja nao, o jogador entra com o nome do prato que ele pensou
		} else if (resposta == JOptionPane.NO_OPTION) {
			Constantes.ACERTOS_JOGADOR ++;
			this.fazTerceiraPergunta(prato);
		}
	}

	/**
	 * faz a terceira pergunta e adiciona o novo prato na arvore
	 * @param prato
	 */
	private void fazTerceiraPergunta(ArvoreAlimento prato) {
		String novoPrato = tela.exibirTelaNovoPrato(Constantes.PERGUNTA_QUAL_PRATO, Constantes.TELA_DESISTO);
		//caso o jogador nao tenha digitado nada finaliza o jogo
		if(novoPrato == null) {
			tela.exibirTelaMensagemErro();
			System.exit(0);
		}
		String pergunta = Constantes.PERGUNTA_APENDICE.replace("?", novoPrato);
		pergunta = pergunta.replace("#", prato.getNome());
		
		String caracteristica = tela.exibirTelaNovoPrato(pergunta, Constantes.TELA_COMPLETE);

		prato.setNoDireito(new ArvoreAlimento(prato.getNome()));
		prato.setNoEsquerdo(new ArvoreAlimento(novoPrato));
		prato.setNome(caracteristica);
	}
	
	public void setTela(Tela tela) {
		this.tela = tela;
	}
}
