package exportwpl.main;

import code.gui.CustComponent;
import code.util.consts.Constants;
import exportwpl.gui.MainWindow;

public class MainClass implements Runnable {

    public static void main(String[] args) {
        CustComponent.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        new MainWindow(Constants.getDefaultLanguage());
    }
}
