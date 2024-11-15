import java.util.List;

public class QuickSort {

    public static void quickSort(List<Pedido> pedidos, int inicio, int fim, int escolha) {
        if (inicio < fim) {
            int indicePivo = particionar(pedidos, inicio, fim, escolha);
            quickSort(pedidos, inicio, indicePivo - 1, escolha);
            quickSort(pedidos, indicePivo + 1, fim, escolha);
        }
    }

    public static int particionar(List<Pedido> pedidos, int inicio, int fim, int escolha) {
        Pedido pivo = pedidos.get(fim); // Usamos o último elemento como pivô
        int i = inicio - 1; // Indice do menor elemento

        for (int j = inicio; j < fim; j++) {
            boolean condicao = false;
            // Dependendo do critério, realizamos a comparação
            switch (escolha) {
                case 1:
                    // Comparação por ID do Pedido (String)
                    condicao = pedidos.get(j).id.compareTo(pivo.id) < 0;
                    break;
                case 2:
                    // Comparação por Data do Pedido (String)
                    condicao = pedidos.get(j).dataPedido.compareTo(pivo.dataPedido) < 0;
                    break;
                case 3:
                    // Comparação por Nome do Cliente (String)
                    condicao = pedidos.get(j).nomeCliente.compareTo(pivo.nomeCliente) < 0;
                    break;
                case 4:
                    // Comparação por Valor Total (double)
                    condicao = pedidos.get(j).valorTotal < pivo.valorTotal;
                    break;
                case 5:
                    // Comparação por Status do Pedido (String)
                    condicao = pedidos.get(j).statusPedido.compareTo(pivo.statusPedido) < 0;
                    break;
                case 6:
                    // Comparação por Quantidade de Itens (int)
                    condicao = pedidos.get(j).quantidadeItens < pivo.quantidadeItens;
                    break;
                case 7:
                    // Comparação por Método de Pagamento (String)
                    condicao = pedidos.get(j).metodoPagamento.compareTo(pivo.metodoPagamento) < 0;
                    break;
                case 8:
                    // Comparação por Data de Entrega Estimada (String)
                    condicao = pedidos.get(j).dataEntregaEstimada.compareTo(pivo.dataEntregaEstimada) < 0;
                    break;
                case 9:
                    // Comparação por Cidade do Cliente (String)
                    condicao = pedidos.get(j).cidadeCliente.compareTo(pivo.cidadeCliente) < 0;
                    break;
                case 10:
                    // Comparação por Categoria Principal do Produto (String)
                    condicao = pedidos.get(j).categoriaProduto.compareTo(pivo.categoriaProduto) < 0;
                    break;
                default:
                    throw new IllegalArgumentException("Critério de ordenação inválido.");
            }

            if (condicao) {
                i++;
                // Troca os elementos
                Pedido temp = pedidos.get(i);
                pedidos.set(i, pedidos.get(j));
                pedidos.set(j, temp);
            }
        }

        // Troca o pivô com o elemento na posição correta
        Pedido temp = pedidos.get(i + 1);
        pedidos.set(i + 1, pedidos.get(fim));
        pedidos.set(fim, temp);

        return i + 1;
    }
}
