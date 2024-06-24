package code.minirts.events;

import code.gui.AbsCustComponent;

import code.maths.geo.RatePoint;
import code.minirts.WindowRts;
import code.minirts.PanelBattle;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;
import code.threads.AbstractAtomicInteger;
import code.threads.ThreadUtil;

public class RtsTask implements Runnable {

    public static final int STOPPED_TASK = 0;
    public static final int ALIVE_TASK = 1;
    private final AbstractAtomicInteger enabled;
    private final Facade facade;

    private RtsDirection dir;

    private final PanelBattle scene;

    private final WindowRts window;

    public RtsTask(PanelBattle _scene, WindowRts _window, Facade _facade) {
        scene = _scene;
        window = _window;
        facade = _facade;
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
        RatePoint loc_ = facade.getTopLeftPoint();
//        rel_.x = -loc_.x;
//        rel_.y = -loc_.y;
//        rel_.x = loc_.x;
//        rel_.y = loc_.y;
        int w_ = par_.getWidth();
        int h_ = par_.getHeight();
        if (dir == RtsDirection.UP) {
            window.moveCamera(loc_, 0, -1);
        } else if (dir == RtsDirection.DOWN) {
            window.moveCamera(loc_, 0, h_+1);
        } else if (dir == RtsDirection.LEFT) {
            window.moveCamera(loc_, -1, 0);
        } else {
            window.moveCamera(loc_, w_+1, 0);
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
