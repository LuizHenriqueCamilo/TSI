import java.math.BigDecimal;
import java.util.Objects;

 class ItemPedido {
    private final Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = Objects.requireNonNull(produto);
        setQuantidade(quantidade);
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva");
        this.quantidade = quantidade;
    }

    public BigDecimal calcularSubtotal() {
        return produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
    }
}

