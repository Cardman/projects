package aiki.gui.listeners;

import aiki.gui.WindowAiki;
import aiki.map.enums.Direction;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseListenerWithoutClick;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

public class MouseTask implements AbsMouseListenerWithoutClick {

    private Direction dir;

    private Task task;

    private final AbstractScheduledExecutorService timer;
    private AbstractFuture future;

    private WindowAiki window;

    public MouseTask(Direction _dir, Task _task, AbstractScheduledExecutorService _timer, WindowAiki _window) {
        dir = _dir;
        task = _task;
        timer = _timer;
        window = _window;
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (window.isClickButtonsPad()) {
            return;
        }
        window.setEnabledMove(true);
        task.setDir(dir);
        future = timer.scheduleAtFixedRate(task,0,0);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (window.isClickButtonsPad()) {
            return;
        }
        future.cancel(true);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (!window.isClickButtonsPad()) {
            return;
        }
        window.setEnabledMove(true);
        task.setDir(dir);
        future = timer.scheduleAtFixedRate(task,0,0);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (!window.isClickButtonsPad()) {
            return;
        }
        future.cancel(true);
    }

//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
}
