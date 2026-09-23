import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    private static final Produto[] CARDAPIO = {
            new Produto(1, "Coxinha", "Lanche", new BigDecimal("6.50"), 10),
            new Produto(2, "Pastel", "Lanche", new BigDecimal("7.00"), 10),
            new Produto(3, "Suco", "Bebida", new BigDecimal("5.00"), 8),
            new Produto(4, "Refrigerante", "Bebida", new BigDecimal("6.00"), 8)
    };

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Pedido pedido = new Pedido(1001);

            System.out.println("=== SISTEMA DE VENDAS DA CANTINA ===");
            escolherProdutos(scanner, pedido);

            if (pedido.getItens().isEmpty()) {
                System.out.println("Nenhum item foi adicionado. Pedido cancelado.");
                return;
            }

            System.out.println("\nTotal do pedido: R$ " + dinheiro(pedido.calcularTotal()));
            Pagamento pagamento = receberPagamento(scanner, pedido.calcularTotal());
            pedido.finalizar(pagamento);
            imprimirComprovante(pedido, pagamento);
        }
    }

    private static void escolherProdutos(Scanner scanner, Pedido pedido) {
        while (true) {
            System.out.println("\n--- CARDÁPIO ---");
            for (Produto produto : CARDAPIO) {
                System.out.printf("%d - %-15s R$ %s | estoque: %d%n",
                        produto.getCodigo(), produto.getNome(),
                        dinheiro(produto.getPrecoUnitario()), produto.getEstoque());
            }
            System.out.println("0 - Finalizar escolha");

            int opcao = lerInteiro(scanner, "Escolha o produto: ");
            if (opcao == 0) return;

            Produto produto = buscarProduto(opcao);
            if (produto == null) {
                System.out.println("Opção inválida.");
                continue;
            }

            int quantidade = lerInteiro(scanner, "Informe a quantidade: ");
            if (!produto.temEstoque(quantidade)) {
                System.out.println("Quantidade indisponível em estoque.");
                continue;
            }

            pedido.adicionarItem(produto, quantidade);
            System.out.println(quantidade + " x " + produto.getNome() + " adicionado ao pedido.");
            System.out.println("Subtotal: R$ " + dinheiro(
                    produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade))));
        }
    }

    private static Pagamento receberPagamento(Scanner scanner, BigDecimal total) {
        while (true) {
            System.out.println("\n--- PAGAMENTO ---");
            System.out.println("1 - Dinheiro");
            System.out.println("2 - PIX");
            System.out.println("3 - Cartão de crédito");
            System.out.println("4 - Cartão de débito");

            int opcao = lerInteiro(scanner, "Escolha a forma de pagamento: ");
            TipoPagamento tipo;
            switch (opcao) {
                case 1 -> tipo = TipoPagamento.DINHEIRO;
                case 2 -> tipo = TipoPagamento.PIX;
                case 3 -> tipo = TipoPagamento.CARTAO_CREDITO;
                case 4 -> tipo = TipoPagamento.CARTAO_DEBITO;
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            BigDecimal valor;
            if (tipo == TipoPagamento.DINHEIRO) {
                valor = lerDinheiro(scanner, "Informe o valor pago: R$ ");
            } else {
                System.out.println("Valor da compra: R$ " + dinheiro(total));
                valor = total;
            }

            Pagamento pagamento = new Pagamento(tipo, valor);
            if (!pagamento.ehSuficiente(total)) {
                System.out.println("Pagamento insuficiente. Faltam R$ "
                        + dinheiro(total.subtract(valor)) + ".");
                continue;
            }
            return pagamento;
        }
    }

    private static void imprimirComprovante(Pedido pedido, Pagamento pagamento) {
        System.out.println("\n=== COMPROVANTE DA CANTINA ===");
        System.out.println("Pedido: " + pedido.getNumero());
        System.out.println("Data/hora: " + pedido.getDataHora());
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("%d x %s = R$ %s%n", item.getQuantidade(),
                    item.getProduto().getNome(), dinheiro(item.calcularSubtotal()));
        }
        System.out.println("Total: R$ " + dinheiro(pedido.calcularTotal()));
        System.out.println("Pagamento: " + pagamento.getTipo());
        System.out.println("Valor pago: R$ " + dinheiro(pagamento.getValorPago()));
        System.out.println("Troco: R$ " + dinheiro(pagamento.calcularTroco(pedido.calcularTotal())));
        System.out.println("Status: " + pedido.getStatus());
    }

    private static Produto buscarProduto(int codigo) {
        for (Produto produto : CARDAPIO) {
            if (produto.getCodigo() == codigo) return produto;
        }
        return null;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= 0) return valor;
            } catch (NumberFormatException ignored) {
                // Continua solicitando até receber um número válido.
            }
            System.out.println("Digite um número válido.");
        }
    }

    private static BigDecimal lerDinheiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                String texto = scanner.nextLine().trim().replace(",", ".");
                BigDecimal valor = new BigDecimal(texto).setScale(2, RoundingMode.HALF_UP);
                if (valor.signum() >= 0) return valor;
            } catch (NumberFormatException ignored) {
                // Continua solicitando até receber um valor válido.
            }
            System.out.println("Digite um valor monetário válido.");
        }
    }

    private static String dinheiro(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ",");
    }
}
