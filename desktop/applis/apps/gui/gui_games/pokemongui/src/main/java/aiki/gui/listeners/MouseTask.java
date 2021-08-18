package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.WindowAiki;
import aiki.map.enums.Direction;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

public class MouseTask extends MouseAdapter {

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
    public void mouseEntered(MouseEvent _e) {
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
    public void mouseExited(MouseEvent _e) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (window.isClickButtonsPad()) {
            return;
        }
        future.cancel(true);
    }

    @Override
    public void mousePressed(MouseEvent _e) {
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
    public void mouseReleased(MouseEvent _e) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (!window.isClickButtonsPad()) {
            return;
        }
        future.cancel(true);
    }

}
