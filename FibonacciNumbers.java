import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.util.concurrent.ExecutionException;

public class FibonacciNumbers extends SwingWorker<Long, Object>
{
    private final int n;
    private final JLabel resultJLabel;

    public FibonacciNumbers(int n, JLabel resultJLabel)
    {
        this.n = n;
        this.resultJLabel = resultJLabel;
    }

    public Long doInBackground()
    {
        return fibonacci(n);
    }

    protected void done()
    {
        try
        {
            resultJLabel.setText(get().toString());
        }
        catch (InterruptedException ex)
        {
            resultJLabel.setText("Houve uma interrupção enquanto se aguardava o resultado.");
        }
        catch (ExecutionException ex)
        {
            resultJLabel.setText(
                "Foi encontrado um erro enquanto executava o cálculo.");
        }
    }

    public long fibonacci(long number)
    {
        if (number == 0 || number == 1)
            return number;
        else
            return fibonacci(number - 1) + fibonacci(number - 2);
    }
}