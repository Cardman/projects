package converterimages.main;
import code.util.consts.Constants;
import converterimages.gui.MainWindow;

/**@author Cardman*/
public final class MainClass {

    /**@param args*/
    public static void main(String[] args) {
//        System.out.println(getForeignPath());
////        Constants.setMainClass(Constants.class);
//        StringList l_ = StreamZipFile.getFilesInJar();
//        System.out.println(l_);
////        Constants.setMainClass(SoftApplication.class);
//        System.out.println(ConstFiles.getForeignPath());
//        StringList l2_ = StreamZipFile.getFilesInJar();
//        System.out.println(l2_);
//        System.out.println(List.equalsSet(l2_, l_));
//        System.out.println(java.lang.Runtime.getRuntime().maxMemory()/1024/1024/1024);
        launch();
    }

    private static MainWindow launch() {
        return new MainWindow();
    }

    public static String getForeignPath() {
        return Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}
