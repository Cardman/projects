package code.minirts.events;

import code.gui.CustComponent;
import code.maths.geo.CustPoint;
import code.minirts.WindowRts;
import code.minirts.PanelBattle;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;

public class RtsTask implements Runnable {

    private final Facade facade;

    private RtsDirection dir;

    private final PanelBattle scene;

    private final WindowRts window;

    public RtsTask(PanelBattle _scene, WindowRts _window, Facade _facade) {
        scene = _scene;
        window = _window;
        facade = _facade;
    }

    @Override
    public void run() {
        if (window.isDragged()) {
            return;
        }
        CustComponent par_ = scene.getContainer().getParent();
        CustPoint loc_ = facade.getTopLeftPoint();
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
        } else if (dir == RtsDirection.RIGHT) {
            window.moveCamera(loc_, w_+1, 0);
        }
    }

    public RtsDirection getDir() {
        return dir;
    }

    public void setDir(RtsDirection _dir) {
        dir = _dir;
    }
}
