package minirts;

import code.gui.CustComponent;
import code.util.consts.Constants;

public final class MainClass implements Runnable {

    public static void main(String[] _args) {
        CustComponent.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        new MainWindow(Constants.getDefaultLanguage());
    }
}
