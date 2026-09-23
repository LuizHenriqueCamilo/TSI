import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final int codigo;
    private final String nome;
    private final String categoria;
    private BigDecimal precoUnitario;
    private int estoque;

    public Produto(int codigo, String nome, String categoria,
                   BigDecimal precoUnitario, int estoque) {
        if (codigo <= 0 || nome == null || nome.isBlank() || precoUnitario == null
                || precoUnitario.signum() < 0 || estoque < 0) {
            throw new IllegalArgumentException("Dados inválidos do produto");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = Objects.requireNonNull(categoria);
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public int getEstoque() { return estoque; }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        if (precoUnitario == null || precoUnitario.signum() < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
        this.precoUnitario = precoUnitario;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");
        estoque += quantidade;
    }

    public void baixarEstoque(int quantidade) {
        if (!temEstoque(quantidade)) throw new IllegalArgumentException("Estoque insuficiente");
        estoque -= quantidade;
    }

    public boolean temEstoque(int quantidade) {
        return quantidade > 0 && estoque >= quantidade;
    }
}
