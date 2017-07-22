package exportwpl.main;
import javax.swing.SwingUtilities;

import exportwpl.gui.MainWindow;

public class MainClass implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        new MainWindow();
    }
}
