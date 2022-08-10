import java.io.IOException;

import javax.swing.JOptionPane;

public class Menu {
	
	public static void main(String[] args) throws IOException {
		byte opcao = 0;
		while (opcao >= 0) {
			String opcaoTxt = JOptionPane.showInputDialog("==xX Menu Xx==\n"
					+ "0:Alterar Arquivo\n"
					+ "1:Novo Produto\n"
					+ "2:Pesquisar por Código\n" 
					+ "3:Exibir Todos\n"
					+ "4:Excluir pelo Código\n"
					+ "9:Fechar");
			if (opcaoTxt == null || opcaoTxt.equals("") || opcaoTxt.equals("4")) {
				opcao = -1;
			}
			try {
				opcao = Byte.parseByte(opcaoTxt);
				switch (opcao) {
				case 0:
					Opcoes.alterarArquivo();
					break;
				case 1:
					Opcoes.novoProduto(null);
					break;
				case 2:
					Opcoes.exibirPorCodigo();
					break;
				case 3:
					Opcoes.exibirProduto();
					break;
				case 4:
					Opcoes.excluirProduto();
					break;
				default:
					opcao = -1;
				}
			} catch (NumberFormatException e) {
				opcao = -1;
				e.printStackTrace();
			}

		}

	}

}
