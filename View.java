import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class View extends JFrame {
    private final JPanel workerJPanel =
        new JPanel(new GridLayout(2,2,5,5));
    private final JTextField numberJTextField = new JTextField();
    private final JButton goJButton = new JButton("Ir");
    private final JLabel fibonacciJLabel = new JLabel();

    // Componentes e variáveis para obter o próximo número de Fibonacci
    private final JPanel eventThreadJPanel =
        new JPanel(new GridLayout(2, 2, 5, 5));
    private long n1 = 0; // inicializa com o primeiro número de Fibonacci
    private long n2 = 1; // inicializa com o segundo número de Fibonacci
    private int count = 1; // número de Fibonacci atual para exibir
    private final JLabel nJLabel = new JLabel("Fibonacci de 1: ");
    private final JLabel nFibonacciJLabel =
        new JLabel (String.valueOf(n2));
    private final JButton nextNumberJButton = new JButton("Próximo");

    // construtor
    public View()
    {
        super(" Números de Fibonacci");
        setLayout(new GridLayout (2, 1, 10, 10));

        // adiciona componentes GUI ao painel SwingWorker
        workerJPanel.setBorder(new TitledBorder(
            new LineBorder(Color.PINK), "Com SwingWorker"));
        workerJPanel.add(new JLabel("Ver Fibonacci de:"));
        workerJPanel.add(numberJTextField);
        goJButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    int n;

                    try
                    {
                        // recupera a entrada do usuário como um inteiro
                        n = Integer.parseInt(numberJTextField.getText());
                    }
                    catch(NumberFormatException ex)
                    {
                        // exibe uma mensagem de erro se o usuário não inseriu
                        // um número inteiro
                        fibonacciJLabel.setText("Enter an integer.");
                        return;
                    }

                    // indica que o cálculo começou
                    fibonacciJLabel.setText("Calculating...");

                    // cria uma tarefa para realizar o cálculo em segundo plano
                    FibonacciNumbers task =
                        new FibonacciNumbers(n, fibonacciJLabel);
                    task.execute(); // executa a tarefa
                }
            } // fim da classe interna anônima
        ); // fim da chamada para addActionListener
        workerJPanel.add(goJButton);
        workerJPanel.add(fibonacciJLabel);

        // adiciona componentes GUI ao painel da thread de despacho de eventos
        eventThreadJPanel.setBorder(new TitledBorder(
            new LineBorder(Color.BLACK), "Without SwingWorker"));
        eventThreadJPanel.add(nJLabel);
        eventThreadJPanel.add(nFibonacciJLabel);
        nextNumberJButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    // calcula o número de Fibonacci após n2
                    long temp = n1 + n2;
                    n1 = n2;
                    n2 = temp;
                    ++count;

                    // exibe o próximo número de Fibonacci
                    nJLabel.setText("Fibonacci of " + count + ": ");
                    nFibonacciJLabel.setText(String.valueOf(n2));
                }
            } // fim da classe interna anônima
        ); // fim da chamada para addActionListener
        eventThreadJPanel.add(nextNumberJButton);

        add(workerJPanel);
        add(eventThreadJPanel);
        setSize(275, 200);
        setVisible(true);
    } // fim do construtor
}
   