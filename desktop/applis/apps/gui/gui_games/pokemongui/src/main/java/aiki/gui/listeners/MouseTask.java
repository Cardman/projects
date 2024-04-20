package aiki.gui.listeners;

import aiki.map.enums.Direction;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerWithoutClickEnter;
import code.threads.AbstractBaseExecutorService;

public class MouseTask implements AbsMouseListenerWithoutClickEnter {

    private final Direction dir;

    private final Task task;

    private final AbstractBaseExecutorService timer;

    public MouseTask(Direction _dir, Task _task, AbstractBaseExecutorService _timer) {
        dir = _dir;
        task = _task;
        timer = _timer;
    }
//
//    @Override
//    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
////        if (window.isEnabledKeyPad()) {
////            return;
////        }
//        if (window.isClickButtonsPad()) {
//            return;
//        }
//        window.setEnabledMove(true);
//        task.setDir(dir);
//        future = timer.scheduleAtFixedRate(task,0,100);
//    }
//
//    @Override
//    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
////        if (window.isEnabledKeyPad()) {
////            return;
////        }
//        if (window.isClickButtonsPad()) {
//            return;
//        }
//        task.getEnabled().set(false);
//        future.cancel(true);
//    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (window.isEnabledKeyPad()) {
//            return;
//        }
//        if (!window.isClickButtonsPad()) {
//            return;
//        }
//        window.setEnabledMove(true);
        task.setDir(dir);
        task.getEnabled().set(Task.ALIVE_TASK);
        timer.submit(task);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        if (window.isEnabledKeyPad()) {
//            return;
//        }
//        if (!window.isClickButtonsPad()) {
//            return;
//        }
        task.getEnabled().set(Task.STOPPED_TASK);
//        future.cancel(true);
    }

//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
}
