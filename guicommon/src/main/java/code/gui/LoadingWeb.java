package code.gui;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import code.gui.document.ProcessingSession;
import code.util.CustList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingWeb implements Runnable {

    //implements Runnable

    private static final int DELTA = 100;

    private ProcessingSession session;

    private ProgressingWebDialog dialog;

    private Timer timer;

    /**This class thread is independant from EDT*/
    public LoadingWeb(ProcessingSession _session, CustList<BufferedImage> _images, Iconifiable _frame, ProgressingWebDialog _dialog) {
        session = _session;
        dialog = _dialog;
        dialog.init(_frame, _images);
        TaskPaintingLabel task_ = new TaskPaintingLabel(dialog);
        timer = new Timer(0, task_);
        timer.setDelay(DELTA);
        timer.start();
    }

//    @Override
//    public void run() {
//        while (session.isProcessing()) {
//            continue;
//        }
//        timer.stop();
//        dialog.setVisible(false);
//        dialog.getContentPane().removeAll();
//    }

    @Override
    public void run() {
        dialog.startAnimation();
        while (session.isProcessing()) {
            continue;
        }
        dialog.stopAnimation();
        timer.stop();
        dialog.setVisible(false);
        dialog.getPane().removeAll();
        dialog = null;
        session = null;
        timer = null;
//        ThreadInvoker.invokeNow(new DoneWebThread(dialog, timer));
    }

//    @Override
//    protected void done() {
//        timer.stop();
//        dialog.setVisible(false);
//        dialog.getContentPane().removeAll();
//    }
}

