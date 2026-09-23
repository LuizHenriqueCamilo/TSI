import java.math.BigDecimal;
import java.util.Objects;

enum TipoPagamento {
    DINHEIRO, PIX, CARTAO_CREDITO, CARTAO_DEBITO
}

public class Pagamento {
    private final TipoPagamento tipo;
    private final BigDecimal valorPago;

    public Pagamento(TipoPagamento tipo, BigDecimal valorPago) {
        this.tipo = Objects.requireNonNull(tipo);
        if (valorPago == null || valorPago.signum() < 0) {
            throw new IllegalArgumentException("Valor pago inválido");
        }
        this.valorPago = valorPago;
    }

    public TipoPagamento getTipo() { return tipo; }
    public BigDecimal getValorPago() { return valorPago; }

    public boolean ehSuficiente(BigDecimal total) {
        return valorPago.compareTo(Objects.requireNonNull(total)) >= 0;
    }

    public BigDecimal calcularTroco(BigDecimal total) {
        if (!ehSuficiente(total)) throw new IllegalStateException("Pagamento insuficiente");
        return valorPago.subtract(total);
    }
}

