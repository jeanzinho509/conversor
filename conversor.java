import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ConversorDeMoeda extends JFrame {
    private JTextField valorTextField;
    private JComboBox<String> moedaComboBox;
    private JLabel resultadoLabel;
    private Map<String, Double> taxasDeCambio;

    public ConversorDeMoeda() {
        // Configurar a janela
        setTitle("Conversor de Moeda");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar as taxas de câmbio
        taxasDeCambio = new HashMap<>();
        taxasDeCambio.put("Dólar (USD)", 5.5);
        taxasDeCambio.put("Euro (EUR)", 6.5); // Adicione mais moedas e taxas de câmbio aqui

        // Criar painel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Rótulo de instrução
        JLabel instrucaoLabel = new JLabel("Digite o valor em reais:");
        panel.add(instrucaoLabel);

        // Campo de texto para entrada
        valorTextField = new JTextField();
        panel.add(valorTextField);

        // JComboBox para selecionar a moeda de destino
        moedaComboBox = new JComboBox<>(taxasDeCambio.keySet().toArray(new String[0]));
        panel.add(moedaComboBox);

        // Botão para converter
        JButton converterButton = new JButton("Converter");
        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                converterMoeda();
            }
        });
        panel.add(converterButton);

        // Rótulo para exibir o resultado
        resultadoLabel = new JLabel();
        panel.add(resultadoLabel);

        // Adicionar painel à janela
        add(panel);

        // Exibir a janela
        setVisible(true);
    }

    private void converterMoeda() {
        try {
            // Obter o valor em reais do campo de texto
            double valorEmReais = Double.parseDouble(valorTextField.getText());

            // Obter a moeda de destino selecionada
            String moedaSelecionada = (String) moedaComboBox.getSelectedItem();

            // Obter a taxa de câmbio para a moeda selecionada
            double taxaDeCambio = taxasDeCambio.get(moedaSelecionada);

            // Calcular a conversão para a moeda de destino
            double valorConvertido = valorEmReais * taxaDeCambio;

            // Exibir o resultado
            resultadoLabel.setText("Valor em " + moedaSelecionada + ": " + valorConvertido);
        } catch (NumberFormatException ex) {
            // Lidar com entrada inválida
            resultadoLabel.setText("Entrada inválida. Digite um número válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConversorDeMoeda();
            }
        });
    }
}
