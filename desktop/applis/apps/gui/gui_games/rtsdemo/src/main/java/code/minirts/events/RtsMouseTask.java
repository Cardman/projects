package code.minirts.events;

import code.minirts.rts.RtsDirection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RtsMouseTask extends MouseAdapter {

    private final RtsDirection dir;

    private final RtsTask task;

    private final Timer timer;

//    private MainWindow window;

    public RtsMouseTask(RtsDirection _dir, RtsTask _task, Timer _timer) {
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
        timer.start();
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        timer.stop();
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
