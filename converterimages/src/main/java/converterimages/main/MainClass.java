package converterimages.main;
import code.util.consts.Constants;
import converterimages.gui.MainWindow;

/**@author Cardman*/
public final class MainClass {

    /**@param args*/
    public static void main(String[] args) {
        launch();
    }

    private static MainWindow launch() {
        return new MainWindow();
    }

    public static String getForeignPath() {
        return Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}
