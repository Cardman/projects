package code.minirts.events;

import code.minirts.rts.RtsDirection;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RtsMouseTask extends MouseAdapter {

    private final RtsDirection dir;

    private final RtsTask task;

    private final AbstractScheduledExecutorService timer;
    private AbstractFuture future;

//    private MainWindow window;

    public RtsMouseTask(RtsDirection _dir, RtsTask _task, AbstractScheduledExecutorService _timer) {
//    , MainWindow _window
        dir = _dir;
        task = _task;
        timer = _timer;
//        window = _window;
    }

//    @Override
//    public void mouseEntered(MouseEvent _e) {
//        if (window.isEnabledKeyPad()) {
//            return;
//        }
//        if (window.isClickButtonsPad()) {
//            return;
//        }
//        window.setEnabledMove(true);
//        task.setDir(dir);
//        timer.start();
//    }
//
//    @Override
//    public void mouseExited(MouseEvent _e) {
//        if (window.isEnabledKeyPad()) {
//            return;
//        }
//        if (window.isClickButtonsPad()) {
//            return;
//        }
//        timer.stop();
//    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        task.setDir(dir);
        future = timer.scheduleAtFixedRate(task, 0, 100);
//        timer.start();
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        future.cancel(true);
//        timer.shutdown();
//        timer.stop();
    }

//    @Override
//    public void mousePressed(MouseEvent _e) {
////        if (window.isEnabledKeyPad()) {
////            return;
////        }
////        if (!window.isClickButtonsPad()) {
////            return;
////        }
////        window.setEnabledMove(true);
//        task.setDir(dir);
//        timer.start();
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent _e) {
////        if (window.isEnabledKeyPad()) {
////            return;
////        }
////        if (!window.isClickButtonsPad()) {
////            return;
////        }
//        timer.stop();
//    }
}
