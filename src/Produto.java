public class Produto {
	
	private short codigo;
	private String nome;
	private float preco;
	
	public Produto(short codigo, String nome, float preco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto() {
		super();
	}

	public short getCodigo() {
		return codigo;
	}
	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "; nome: " + nome + "; R$" + preco + "\n";
	}
	
}
