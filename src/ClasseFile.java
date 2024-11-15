import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class ClasseFile{
public static List<Pedido> lerArquivo(String nomeArquivo) throws IOException {
    List<Pedido> pedidos = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(" \\| ");
            Pedido pedido = new Pedido(
                    campos[0],                         // ID do Pedido
                    campos[1],                         // Data do Pedido
                    campos[2],                         // Nome do Cliente
                    Double.parseDouble(campos[3]),     // Valor Total
                    campos[4],                         // Status do Pedido
                    Integer.parseInt(campos[5]),       // Quantidade de Itens
                    campos[6],                         // Método de Pagamento
                    campos[7],                         // Data de Entrega Estimada
                    campos[8],                         // Cidade do Cliente
                    campos[9]                          // Categoria Principal do Produto
            );
            pedidos.add(pedido);
        }
    }
    return pedidos;
}

public static void escreverArquivo(String nomeArquivo, List<Pedido> pedidos) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
        for (Pedido pedido : pedidos) {
            bw.write(pedido.toString());
            bw.newLine();
        }
    }
}
}