import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final long numero;
    private final LocalDateTime dataHora;
    private final List<ItemPedido> itens = new ArrayList<>();
    private Pagamento pagamento;
    private StatusPedido status = StatusPedido.ABERTO;

    public Pedido(long numero) {
        if (numero <= 0) throw new IllegalArgumentException("Número inválido");
        this.numero = numero;
        this.dataHora = LocalDateTime.now();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (status == StatusPedido.FINALIZADO) throw new IllegalStateException("Pedido já finalizado");
        if (!produto.temEstoque(quantidade)) throw new IllegalArgumentException("Estoque insuficiente");
        itens.add(new ItemPedido(produto, quantidade));
    }

    public BigDecimal calcularTotal() {
        return itens.stream().map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void finalizar(Pagamento pagamento) {
        Objects.requireNonNull(pagamento);
        if (itens.isEmpty()) throw new IllegalStateException("O pedido deve possuir itens");
        if (!pagamento.ehSuficiente(calcularTotal())) throw new IllegalStateException("Pagamento insuficiente");
        for (ItemPedido item : itens) item.getProduto().baixarEstoque(item.getQuantidade());
        this.pagamento = pagamento;
        this.status = StatusPedido.FINALIZADO;
    }

    public long getNumero() { return numero; }
    public LocalDateTime getDataHora() { return dataHora; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }
    public Pagamento getPagamento() { return pagamento; }
    public StatusPedido getStatus() { return status; }
}

enum StatusPedido { ABERTO, FINALIZADO }
