import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class PedidosInterface extends JFrame {
    private List<Pedido> pedidos;
    private JComboBox<String> criterioComboBox;
    private JButton ordenarButton;
    private JButton salvarButton;
    private JFileChooser fileChooser;
    private String caminhoEntrada = "src\\pedidos_quicksort.txt"; // Caminho do arquivo de entrada
    private JTable pedidosTable;
    private DefaultTableModel tableModel;

    public PedidosInterface() {
        // Título da janela
        setTitle("Ordenação de Pedidos");
        // Tamanho da janela
        setSize(1080, 720);
        // Comportamento ao fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centraliza a janela
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout()); // Usando BorderLayout para melhor controle dos componentes

        // Criação dos componentes da interface
        criterioComboBox = new JComboBox<>(new String[]{
                "ID do Pedido", "Data do Pedido", "Nome do Cliente", "Valor Total",
                "Status do Pedido", "Quantidade de Itens", "Método de Pagamento",
                "Data de Entrega Estimada", "Cidade do Cliente", "Categoria Principal do Produto"
        });

        ordenarButton = new JButton("Ordenar");
        salvarButton = new JButton("Salvar Arquivo");

        // Configuração do JFileChooser
        fileChooser = new JFileChooser();

        // Criação do modelo de dados para a JTable
        String[] colunas = {"ID", "Data Pedido", "Nome Cliente", "Valor Total", "Status",
                "Qtde Itens", "Método Pagamento", "Data Entrega", "Cidade", "Categoria"};
        tableModel = new DefaultTableModel(colunas, 0);
        pedidosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(pedidosTable); // Adiciona a barra de rolagem para a tabela

        // Painel para os botões
        JPanel panelButtons = new JPanel();
        panelButtons.add(new JLabel("Escolha o critério de ordenação:"));
        panelButtons.add(criterioComboBox);
        panelButtons.add(ordenarButton);
        panelButtons.add(salvarButton);

        // Adiciona os componentes ao layout
        add(panelButtons, BorderLayout.NORTH); // Coloca os botões no topo (Norte)
        add(scrollPane, BorderLayout.CENTER);   // Coloca a tabela no centro (ela vai ocupar o restante do espaço)

        // Ações dos botões
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ler o arquivo de pedidos
                    pedidos = ClasseFile.lerArquivo(caminhoEntrada);

                    // Exibir os pedidos na JTable antes de ordenar
                    exibirPedidosNaTabela(pedidos);

                    // Obter a opção selecionada no JComboBox
                    int escolha = criterioComboBox.getSelectedIndex() + 1;

                    // Realizar a ordenação com QuickSort
                    QuickSort.quickSort(pedidos, 0, pedidos.size() - 1, escolha);

                    // Atualiza a tabela com os pedidos ordenados
                    exibirPedidosNaTabela(pedidos);

                    JOptionPane.showMessageDialog(PedidosInterface.this,
                            "Pedidos ordenados com sucesso!");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(PedidosInterface.this,
                            "Erro ao ler o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configurar o JFileChooser para salvar o arquivo
                fileChooser.setDialogTitle("Escolha onde salvar o arquivo");
                fileChooser.setSelectedFile(new java.io.File("pedidos_ordenados_quicksort.txt")); // Nome padrão sugerido

                int userSelection = fileChooser.showSaveDialog(PedidosInterface.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Obter o caminho escolhido pelo usuário
                    String caminhoSaida = fileChooser.getSelectedFile().getAbsolutePath();

                    try {
                        // Salvar os pedidos ordenados no caminho escolhido
                        ClasseFile.escreverArquivo(caminhoSaida, pedidos);
                        JOptionPane.showMessageDialog(PedidosInterface.this,
                                "Arquivo salvo com sucesso em: " + caminhoSaida);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(PedidosInterface.this,
                                "Erro ao salvar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    // Método para exibir os pedidos na JTable
    private void exibirPedidosNaTabela(List<Pedido> pedidos) {
        // Limpa a tabela antes de adicionar os novos dados
        tableModel.setRowCount(0);

        for (Pedido pedido : pedidos) {
            Object[] dados = {
                    pedido.id, pedido.dataPedido, pedido.nomeCliente, pedido.valorTotal,
                    pedido.statusPedido, pedido.quantidadeItens, pedido.metodoPagamento,
                    pedido.dataEntregaEstimada, pedido.cidadeCliente, pedido.categoriaProduto
            };
            tableModel.addRow(dados); // Adiciona uma nova linha na tabela
        }
    }

    public static void main(String[] args) {
        // Chama o método que inicializa e exibe a interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PedidosInterface interfaceGrafica = new PedidosInterface();
                interfaceGrafica.setVisible(true);  // Tornar a janela visível
            }
        });
    }
}
