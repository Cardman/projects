package minirts;

import javax.swing.SwingUtilities;

import code.imagesurl.data.Handler;


public final class MainClass extends Thread {

    public static void main(String[] _args) {
        SwingUtilities.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        Handler.install();
        new MainWindow();
    }
}
