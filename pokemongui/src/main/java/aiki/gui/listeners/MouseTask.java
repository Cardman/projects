package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import aiki.gui.MainWindow;
import aiki.map.enums.Direction;

public class MouseTask extends MouseAdapter {

    private Direction dir;

    private Task task;

    private Timer timer;

    private MainWindow window;

    public MouseTask(Direction _dir, Task _task, Timer _timer, MainWindow _window) {
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
        timer.start();
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (window.isClickButtonsPad()) {
            return;
        }
        timer.stop();
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
        timer.start();
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (window.isEnabledKeyPad()) {
            return;
        }
        if (!window.isClickButtonsPad()) {
            return;
        }
        timer.stop();
    }
}
