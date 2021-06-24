package code.gui;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import code.gui.document.ProcessingSession;
import code.gui.images.AbstractImage;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingWeb implements Runnable {

    //implements Runnable

    private static final int DELTA = 100;

    private ProcessingSession session;

    private ProgressingWebDialog dialog;

    private Timer timer;
    private final AbstractThreadFactory fact;

    /**This class thread is independant from EDT*/
    public LoadingWeb(AbstractThreadFactory _fact, ProcessingSession _session, CustList<AbstractImage> _images, Iconifiable _frame, ProgressingWebDialog _dialog) {
        fact = _fact;
        session = _session;
        dialog = _dialog;
        dialog.init(_fact,_session,_frame, _images);
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
        dialog.startAnimation(fact);
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

