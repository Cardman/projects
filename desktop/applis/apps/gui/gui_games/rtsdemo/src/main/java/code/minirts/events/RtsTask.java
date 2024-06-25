package code.minirts.events;

import code.minirts.*;
import code.minirts.rts.*;
import code.threads.*;

public final class RtsTask implements Runnable {

    public static final int STOPPED_TASK = 0;
    public static final int ALIVE_TASK = 1;
    private final AbstractAtomicInteger enabled;

    private RtsDirection dir;

    private final WindowRts window;

    public RtsTask(WindowRts _window) {
        window = _window;
        enabled = _window.getThreadFactory().newAtomicInteger();
    }

    @Override
    public void run() {
        while (window.getTaskEnabled().status(enabled) == ALIVE_TASK) {
            paint();
            ThreadUtil.sleep(window.getThreadFactory(), 100);
        }
    }

    private void paint() {
//        if (window.isDragged()) {
//            return;
//        }
//        AbsCustComponent par_ = scene.getContainer().getParent();
//        rel_.x = -loc_.x;
//        rel_.y = -loc_.y;
//        rel_.x = loc_.x;
//        rel_.y = loc_.y;
//        int w_ = par_.getWidth();
//        int h_ = par_.getHeight();
        RtsDirection dir_ = getDir();
        if (dir_ == RtsDirection.UP) {
            window.moveCamera(0, -1);
        } else if (dir_ == RtsDirection.DOWN) {
            window.moveCamera(0, 1);
        } else if (dir_ == RtsDirection.LEFT) {
            window.moveCamera(-1, 0);
        } else {
            window.moveCamera(1, 0);
        }
    }

    public RtsDirection getDir() {
        return dir;
    }

    public void setDir(RtsDirection _dir) {
        dir = _dir;
    }

    public AbstractAtomicInteger getEnabled() {
        return enabled;
    }
}
