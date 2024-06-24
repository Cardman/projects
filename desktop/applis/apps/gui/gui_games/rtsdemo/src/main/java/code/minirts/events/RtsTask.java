package code.minirts.events;

import code.gui.*;
import code.minirts.*;
import code.minirts.rts.*;
import code.threads.*;

public class RtsTask implements Runnable {

    public static final int STOPPED_TASK = 0;
    public static final int ALIVE_TASK = 1;
    private final AbstractAtomicInteger enabled;

    private RtsDirection dir;

    private final PanelBattle scene;

    private final WindowRts window;

    public RtsTask(PanelBattle _scene, WindowRts _window) {
        scene = _scene;
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
        AbsCustComponent par_ = scene.getContainer().getParent();
//        rel_.x = -loc_.x;
//        rel_.y = -loc_.y;
//        rel_.x = loc_.x;
//        rel_.y = loc_.y;
        int w_ = par_.getWidth();
        int h_ = par_.getHeight();
        if (dir == RtsDirection.UP) {
            window.moveCamera(0, -1);
        } else if (dir == RtsDirection.DOWN) {
            window.moveCamera(0, h_+1);
        } else if (dir == RtsDirection.LEFT) {
            window.moveCamera(-1, 0);
        } else {
            window.moveCamera(w_+1, 0);
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
