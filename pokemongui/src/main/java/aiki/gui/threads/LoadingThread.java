package aiki.gui.threads;
import javax.swing.SwingUtilities;

import aiki.DataBase;
import aiki.gui.MainWindow;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread extends Thread {

    private MainWindow window;

    private String fileName;

//    private LoadGame opening;

    /**This class thread is independant from EDT*/
    public LoadingThread(MainWindow _window, String _fileName) {
        window = _window;
        fileName = _fileName;
//        opening = _opening;
    }

    @Override
    public void run() {
        boolean error_ = false;
        try {
//            if (!new File(fileName).exists() || !Constants.isZippedFile(fileName)) {
//                throw new FileNotFoundException(fileName);
//            }
            window.processLoad(fileName);
        } catch (Exception _0) {
            error_ = true;
            //NumericString.setCheckSyntax(false);
            _0.printStackTrace();
        } catch (VirtualMachineError _0) {
            error_ = true;
            //NumericString.setCheckSyntax(false);
            _0.printStackTrace();
        }
        //window.getDialog().dispose();
        boolean wasLoading_ = DataBase.isLoading();
        DataBase.setLoading(false);
        if (!wasLoading_) {
            MainWindow.getDialog().setVisible(false);
            return;
        }
        //window.getDialog().dispose();
//        try {
//            opening.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        SwingUtilities.invokeLater(new AfterLoadingThread(window, fileName, error_));
        window = null;
//        if (error_) {
//            window.showErrorMessageDialog(fileName);
//        } else {
//            window.showSuccessfulMessageDialog(fileName);
//        }
    }
}
