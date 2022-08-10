import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Opcoes {

	public static String FILE_PATH = "C:\\Users\\estev\\OneDrive\\Área de Trabalho\\produtos.txt";
	public static String LOG_PATH = "C:\\Users\\estev\\OneDrive\\Área de Trabalho\\log.txt";

	public static void alterarArquivo() { //comando 0
		final JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		fc.showOpenDialog(null);
		File[] files = fc.getSelectedFiles();
		for (File f : files) {
			System.out.println(f.getAbsoluteFile());
		}
	}
	
	public static void novoProduto(Produto obj) throws IOException { //comando 1
		String nome = JOptionPane.showInputDialog("Informe o nome do produto:");
		short codigo = Short.parseShort(JOptionPane.showInputDialog("Informe o codigo:"));
		float preco = Float.parseFloat(JOptionPane.showInputDialog("Informe o preco:"));
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setCodigo(codigo);
		produto.setPreco(preco);
		insereController(produto);
		//String produtoCSV = produto.toString();
	}
	 public static void insereModel(Produto obj) throws IOException { // insere produto + log
		try {
	        File  f = new File(FILE_PATH);
	        FileOutputStream fos = new FileOutputStream(f, true);
	        DataOutputStream dos = new DataOutputStream(fos);
	        dos.write(new String(obj.toString()).getBytes());
	        dos.close();
	        fos.close();
		} catch (Exception e) {
			
		}
		logAcesso("Inseriu Produto");
		
	}

	 public static void exibirPorCodigo() throws IOException { //comando 2
			Produto produto = new Produto();
			produto.setCodigo(Short.parseShort(JOptionPane.showInputDialog("Digite o codigo do produto")));
	// exibirPorCodigo(String.valueOf(codigo));
			exibirPorCodigo(produto.getCodigo());
			//JOptionPane.showInternalMessageDialog(null, produto);
		}
	 public static void exibirPorCodigo(short codigo) throws IOException {
			File f = new File(FILE_PATH);
			Produto produto = new Produto();
			try {
				FileInputStream fis = new FileInputStream(f);
				byte[] dados = fis.readAllBytes();
				String conteudoArquivo = new String(dados);
				String[] linhas = conteudoArquivo.split("\n");
				fis.close();
				String procura = "codigo: " + codigo +";";
				for(int i=0; i<linhas.length; i++) {
					if(linhas[i].contains(procura)) {
						JOptionPane.showMessageDialog(null, linhas[i].replaceAll(";", "-"));
					}
				}
				/*for (String linha : linhas) {
					String[] colunas = linha.split(";");
					if (!colunas[0].replace("codigo: ", "").equals(String.valueOf(codigo))) {
						produto.setNome(colunas[1]);
						produto.setCodigo(Short.parseShort(colunas[0]));
						produto.setPreco(Float.parseFloat(colunas[2]));
					}
					
				}*/
				
				fis.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			Opcoes.logAcesso("Exibiu por código");
		}
	
	public static void exibirProduto() throws IOException { //comando 3
		String conteudo = exibeModel();
		int linhas = conteudo.split("\n").length;
		JOptionPane.showMessageDialog(null, "Total de itens:" + linhas + "\n" + conteudo+ "\n");
	}

	public static String exibeModel() throws IOException {
		String retorno = "";
		File f = new File(FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] conteudo = fis.readAllBytes();
			retorno = new String(conteudo);
			retorno = retorno.replaceAll(";","-");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logAcesso("Exibiu Produto");
		return retorno;
	}
	
	public static void excluirProduto() throws IOException { //comando 4
		Short codigo = Short.parseShort(JOptionPane.showInputDialog("Informe o Código:"));
		excluirModel(String.valueOf(codigo));
	}
	
	public static void excluirModel(String codigo) throws IOException {

		File f = new File(FILE_PATH);
		String retorno = "";

		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] conteudo = fis.readAllBytes();
			retorno = new String(conteudo);
			String[] linhas = retorno.split("\n");
			String novoTexto = "";

			for (String linha : linhas) {
				String[] colunas = linha.split(";");
				if (!colunas[0].replace("codigo: ", "").equals(codigo)) {
					novoTexto = novoTexto + linha + "\n";
				}
			}

			FileOutputStream fos = new FileOutputStream(f, false);
			fos.write(new String(novoTexto).getBytes());
			fos.close();
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		logAcesso("Excluiu Produto");

	}
	
	public static void logAcesso(String mensagem) throws IOException {//inserir log na pasta log.txt
		String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		mensagem = localTime + " - " + mensagem;
		File f = new File(LOG_PATH);
		FileOutputStream fos = new FileOutputStream(f, true);
		fos.write(mensagem.getBytes());
		fos.write("\n".getBytes());
		fos.close();

	}
	
	public static void insereController(Produto obj) throws IOException { //Obj para inserir log (Controller)
		insereModel(obj);
	}

}
