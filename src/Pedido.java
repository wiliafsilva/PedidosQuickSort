public class Pedido {
    String id;
    String dataPedido;
    String nomeCliente;
    double valorTotal;
    String statusPedido;
    int quantidadeItens;
    String metodoPagamento;
    String dataEntregaEstimada;
    String cidadeCliente;
    String categoriaProduto;

    public Pedido(String id, String dataPedido, String nomeCliente, double valorTotal,
                  String statusPedido, int quantidadeItens, String metodoPagamento,
                  String dataEntregaEstimada, String cidadeCliente, String categoriaProduto) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
        this.quantidadeItens = quantidadeItens;
        this.metodoPagamento = metodoPagamento;
        this.dataEntregaEstimada = dataEntregaEstimada;
        this.cidadeCliente = cidadeCliente;
        this.categoriaProduto = categoriaProduto;
    }
    @Override
    public String toString(){
        return id + "|" + dataPedido + " | " + nomeCliente + " | " + valorTotal + " | " +
                statusPedido + " | " + quantidadeItens + " | " + metodoPagamento + " | " +
                dataEntregaEstimada + " | " + cidadeCliente + " | " + categoriaProduto;
    }
}
