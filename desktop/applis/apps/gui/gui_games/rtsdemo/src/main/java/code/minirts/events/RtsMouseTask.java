package code.minirts.events;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerWithoutClickPr;
import code.minirts.rts.RtsDirection;
import code.threads.*;

public final class RtsMouseTask implements AbsMouseListenerWithoutClickPr {

    private final RtsDirection dir;

    private final RtsTask task;

    private final AbstractBaseExecutorService timer;
//    private AbstractFuture future;

//    private MainWindow window;

    public RtsMouseTask(RtsDirection _dir, RtsTask _task, AbstractBaseExecutorService _timer) {
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
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        task.setDir(dir);
        task.getEnabled().set(RtsTask.ALIVE_TASK);
        timer.submitLater(task);

//        future = timer.scheduleAtFixedRate(task, 0, 100);
//        timer.start();
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        future.cancel(true);
        task.getEnabled().set(RtsTask.STOPPED_TASK);
//        timer.shutdown();
//        timer.stop();
    }

    public AbstractBaseExecutorService getTimer() {
        return timer;
    }

//    @Override
//    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }

//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }

//    @Override
//    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }

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
